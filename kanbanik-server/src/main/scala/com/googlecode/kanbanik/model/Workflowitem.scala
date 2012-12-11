package com.googlecode.kanbanik.model
import org.bson.types.ObjectId
import com.mongodb.casbah.Imports.$set
import com.mongodb.casbah.MongoConnection
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.BasicDBList
import com.mongodb.DBObject
import com.googlecode.kanbanik.db.HasMidAirCollisionDetection
import com.googlecode.kanbanik.db.HasMongoConnection
import com.googlecode.kanbanik.dto.ItemType

class Workflowitem(
  val id: Option[ObjectId],
  val name: String,
  val wipLimit: Int,
  val itemType: String,
  val version: Int,
  val nestedWorkflow: Workflow,
  val _parentWorkflow: Option[Workflow])
  extends HasMongoConnection
  with HasMidAirCollisionDetection with Equals {

  def this(id: Option[ObjectId],
    name: String,
    wipLimit: Int,
    itemType: String,
    version: Int,
    nestedWorkflow: Workflow) = this(id, name, wipLimit, itemType, version, nestedWorkflow, None)

  def parentWorkflow: Workflow = _parentWorkflow.getOrElse(loadWorkflow)

  private def loadWorkflow(): Workflow = {
    using(createConnection) { conn =>
      val dbWorkflow = coll(conn, Coll.Workflow).findOne(
        MongoDBObject(Workflow.Fields.workflowitems.toString -> MongoDBObject(Workflowitem.Fields.id.toString -> id.get))).getOrElse(throw new IllegalStateException("The workflowitem has to exist on a board!"))
      Workflow.asEntity(dbWorkflow)
    }
  }

  // it has to be possible to do this in some cleaner way
  def withName(name: String) =
    new Workflowitem(
      id,
      name,
      wipLimit,
      itemType,
      version,
      nestedWorkflow,
      _parentWorkflow);

  def withWipLimit(wipLimit: Int) =
    new Workflowitem(
      id,
      name,
      wipLimit,
      itemType,
      version,
      nestedWorkflow,
      _parentWorkflow);

  def withItemType(itemType: String) =
    new Workflowitem(
      id,
      name,
      wipLimit,
      itemType,
      version,
      nestedWorkflow,
      _parentWorkflow);

  def withVersion(version: Int) =
    new Workflowitem(
      id,
      name,
      wipLimit,
      itemType,
      version,
      nestedWorkflow,
      _parentWorkflow);

  def withWorkflow(newWorkflow: Workflow) =
    new Workflowitem(
      id,
      name,
      wipLimit,
      itemType,
      version,
      newWorkflow,
      _parentWorkflow);

  def withParentWorkflow(parentWorkflow: Workflow) =
    new Workflowitem(
      id,
      name,
      wipLimit,
      itemType,
      version,
      nestedWorkflow,
      Some(parentWorkflow));
  
  def store: Workflowitem = {
    using(createConnection) { conn =>
      val update = $set(
        Workflowitem.Fields.version.toString() -> { version + 1 },
        Workflowitem.Fields.name.toString() -> name,
        Workflowitem.Fields.wipLimit.toString() -> wipLimit,
        Workflowitem.Fields.itemType.toString() -> itemType,
        Workflowitem.Fields.nestedWorkflow.toString() -> nestedWorkflow)

      Workflowitem.asEntity(versionedUpdate(Coll.Workflowitems, versionedQuery(id.getOrElse(throw new UnsupportedOperationException("It is not possible to create a standalone workflowitem - it has to belong to a board")), version), update))
    }
  }

  def storeData: Workflowitem = {
    val update = $set(
      Workflowitem.Fields.version.toString() -> { version + 1 },
      Workflowitem.Fields.name.toString() -> name,
      Workflowitem.Fields.wipLimit.toString() -> wipLimit,
      Workflowitem.Fields.itemType.toString() -> itemType,
      Workflowitem.Fields.nestedWorkflow.toString() -> nestedWorkflow.asDbObject)

    Workflowitem.asEntity(versionedUpdate(Coll.Workflowitems, versionedQuery(id.get, version), update))
  }

  def delete {
    versionedDelete(Coll.Workflowitems, versionedQuery(id.get, version))
  }

  def asDbObject(): DBObject = {
    MongoDBObject(
      Workflowitem.Fields.id.toString() -> id.getOrElse(new ObjectId),
      Workflowitem.Fields.name.toString() -> name,
      Workflowitem.Fields.wipLimit.toString() -> wipLimit,
      Workflowitem.Fields.itemType.toString() -> itemType,
      Workflowitem.Fields.version.toString() -> version,
      Workflowitem.Fields.nestedWorkflow.toString() -> nestedWorkflow.asDbObject)
  }

  def canEqual(other: Any) = {
    other.isInstanceOf[com.googlecode.kanbanik.model.Workflowitem]
  }

  override def equals(other: Any) = {
    other match {
      case that: Workflowitem => that.canEqual(Workflowitem.this) && id == that.id
      case _ => false
    }
  }

  override def hashCode() = {
    val prime = 41
    prime + id.hashCode
  }

  override def toString = id.toString

}

object Workflowitem extends HasMongoConnection {

  object Fields extends DocumentField {
    val wipLimit = Value("wipLimit")
    val itemType = Value("itemType")
    val nestedWorkflow = Value("nestedWorkflow")
  }

  def apply() = new Workflowitem(Some(new ObjectId()), "", -1, ItemType.HORIZONTAL.asStringValue(), 1, Workflow(), None)
  
  def all(): List[Workflowitem] = {
    using(createConnection) { conn =>
      coll(conn, Coll.Workflowitems).find().map(asEntity(_)).toList
    }
  }

  def byId(id: ObjectId): Workflowitem = {
    using(createConnection) { conn =>
      val dbWorkflow = coll(conn, Coll.Workflowitems).findOne(MongoDBObject(Fields.id.toString() -> id)).getOrElse(throw new IllegalArgumentException("No such workflowitem with id: " + id))
      asEntity(dbWorkflow)
    }
  }

  def asEntity(dbObject: DBObject, workflow: Option[Workflow]): Workflowitem = {
    new Workflowitem(
      Some(dbObject.get(Fields.id.toString()).asInstanceOf[ObjectId]),
      dbObject.get(Fields.name.toString()).asInstanceOf[String],
      dbObject.get(Fields.wipLimit.toString()).asInstanceOf[Int],
      dbObject.get(Fields.itemType.toString()).asInstanceOf[String],
      dbObject.get(Fields.version.toString()).asInstanceOf[Int],
      {
        val nestedWorkflow = dbObject.get(Fields.nestedWorkflow.toString()).asInstanceOf[DBObject]
        Workflow.asEntity(nestedWorkflow)
      },
      workflow)
  }

  def asEntity(dbObject: DBObject): Workflowitem = {
    asEntity(dbObject, None)
  }

}