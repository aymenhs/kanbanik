package com.googlecode.kanbanik.model
import org.bson.types.ObjectId
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.DBObject
import com.mongodb.BasicDBList
import com.sun.org.apache.xalan.internal.xsltc.compiler.ForEach
import com.mongodb.casbah.Imports._
import java.util.ArrayList

class Board(
  var id: Option[ObjectId],
  var name: String,
  var version: Int,
  var workflowitems: Option[List[Workflowitem]]) extends HasMongoConnection {

  def store: Board = {
    val idToUpdate = id.getOrElse({
      val obj = Board.asDBObject(this)
      using(createConnection) {
        connection => coll(connection, Coll.Boards) += obj
      }

      return Board.asEntity(obj)
    })

    val idObject = MongoDBObject(Board.Fields.id.toString() -> idToUpdate)

    using(createConnection) { conn =>
      
      var query = (MongoDBObject(Board.Fields.id.toString() -> id)) ++ ($or((Board.Fields.version.toString() -> MongoDBObject("$exists" -> false)), (Board.Fields.version.toString() -> version)))
      
      val update = $set(
        Board.Fields.version.toString() -> { version + 1 },
        Board.Fields.name.toString() -> name,
        Board.Fields.workflowitems.toString() -> {
          if (workflowitems.isDefined) {
            for { x <- workflowitems.get } yield x.id
          } else {
            null
          }
        })

      val res = coll(conn, Coll.Boards).findAndModify(query, null, null, false, update, true, false)
      Board.asEntity(res.getOrElse(throw new MidAirCollisionException))
    }

  }

  def delete {
    using(createConnection) { conn =>
      coll(conn, Coll.Boards).remove(MongoDBObject(Board.Fields.id.toString() -> id))
    }
  }

}

object Board extends HasMongoConnection {

  object Fields extends DocumentField {
    val workflowitems = Value("workflowitems")
  }

  def all(): List[Board] = {
    var allBoards = List[Board]()
    using(createConnection) { conn =>
      coll(conn, Coll.Boards).find().foreach(board => allBoards = asEntity(board) :: allBoards)
    }
    allBoards
  }

  def byId(id: ObjectId): Board = {
    using(createConnection) { conn =>
      val dbBoards = coll(conn, Coll.Boards).findOne(MongoDBObject(Board.Fields.id.toString() -> id)).getOrElse(throw new IllegalArgumentException("No such board with id: " + id))
      asEntity(dbBoards)
    }
  }

  private def asEntity(dbObject: DBObject) = {
    new Board(
      Some(dbObject.get(Board.Fields.id.toString()).asInstanceOf[ObjectId]),
      dbObject.get(Board.Fields.name.toString()).asInstanceOf[String],
      {
        val res = dbObject.get(Board.Fields.version.toString())
        if (res == null) {
          1
        } else {
          res.asInstanceOf[Int]
        }
      },
      {
        if (dbObject.get(Board.Fields.workflowitems.toString()).isInstanceOf[BasicDBList]) {
          Some(for { x <- dbObject.get(Board.Fields.workflowitems.toString()).asInstanceOf[BasicDBList].toArray().toList } yield Workflowitem.byId(x.asInstanceOf[ObjectId]))
        } else {
          None
        }
      })
  }

  private def asDBObject(entity: Board): DBObject = {
    MongoDBObject(
      Board.Fields.id.toString() -> new ObjectId,
      Board.Fields.name.toString() -> entity.name,
      Board.Fields.version.toString() -> entity.version,
      Board.Fields.workflowitems.toString() -> {
        if (!entity.workflowitems.isDefined) {
          null
        } else {
          for { x <- entity.workflowitems.get } yield x.id
        }
      })
  }
}