# Introduction #

In version 0.2.6 the new JSON based API has been introduced to Kanbanik. This API is still just experimental so can change anytime.

# Introduction #

The API supports both POST a GET HTTP requests. An example of a GET request for login:

```
http://localhost:4041/kanbanik/api?command={"commandName":"login","userName":"admin","password":"admin"}
```

All commands which are sent to server are a JSON document which is a value of the **command** arg. All commands start with a **"commandName":"..."** which determines the command and than the rest of the params of the command (in this example the commandName is login and the parameters of the login command are userName and password).

The response from server for the login command is something like this:
```
{"userName":"admin","realName":"Default User","pictureUrl":"","sessionId":"e63c51a1-c316-497e-bcfa-6f264e7714d1","version":3}
```

which is the JSON description of the user "admin". Please note the **sessionId** and the **version** attributes.
  * sessionId: this session ID has to be stored sent with all the commands to the server for authorization purposes. This sessionId is valid for 8h
  * version: the version for pessimistic locking. Has to be sent to server on update.

So now, when we have successfully logged in and have the sessionId it is possible to request the main query of the application which returns all the boards, projects and tasks (in the Kanbanik web application used when loading the main screen of kanbanik).
```
http://localhost:4041/kanbanik/api?command={"commandName":"getAllBoardsWithProjects","includeTasks":true,"sessionId":"e63c51a1-c316-497e-bcfa-6f264e7714d1"}
```

(Please note the sessionId included).
In this example there is one board, one project and three workflowitems on it and on the first workflowitem three tasks.
```
{
   "values":[
      {
         "board":{
            "id":"536e4d8e8ea442a8f6dab2b1",
            "version":12,
            "name":"MyBoard",
            "workflowVerticalSizing":-1,
            "workflow":{
               "id":"536e4d8e8ea442a8f6dab2b0",
               "workflowitems":[
                  {
                     "name":"todo",
                     "id":"536e4d928ea442a8f6dab2b6",
                     "wipLimit":-1,
                     "itemType":"H",
                     "version":1,
                     "nestedWorkflow":{
                        "id":"536e4d928ea442a8f6dab2b8",
                        "workflowitems":[

                        ],
                        "board":{
                           "id":"536e4d8e8ea442a8f6dab2b1",
                           "version":12,
                           "name":"MyBoard",
                           "workflowVerticalSizing":-1,
                           "showUserPictureEnabled":true
                        }
                     },
                     "parentWorkflow":{
                        "id":"536e4d8e8ea442a8f6dab2b0",
                        "board":{
                           "id":"536e4d8e8ea442a8f6dab2b1",
                           "version":12,
                           "name":"MyBoard",
                           "workflowVerticalSizing":-1,
                           "showUserPictureEnabled":true
                        }
                     },
                     "verticalSize":-1
                  },
                  {
                     "name":"doing",
                     "id":"536f6cb28ea433d19fca1e7d",
                     "wipLimit":-1,
                     "itemType":"H",
                     "version":1,
                     "nestedWorkflow":{
                        "id":"536f6cb28ea433d19fca1e82",
                        "workflowitems":[

                        ],
                        "board":{
                           "id":"536e4d8e8ea442a8f6dab2b1",
                           "version":12,
                           "name":"MyBoard",
                           "workflowVerticalSizing":-1,
                           "showUserPictureEnabled":true
                        }
                     },
                     "parentWorkflow":{
                        "id":"536e4d8e8ea442a8f6dab2b0",
                        "board":{
                           "id":"536e4d8e8ea442a8f6dab2b1",
                           "version":12,
                           "name":"MyBoard",
                           "workflowVerticalSizing":-1,
                           "showUserPictureEnabled":true
                        }
                     },
                     "verticalSize":-1
                  },
                  {
                     "name":"done",
                     "id":"536e4d938ea442a8f6dab2be",
                     "wipLimit":-1,
                     "itemType":"H",
                     "version":1,
                     "nestedWorkflow":{
                        "id":"536e4d938ea442a8f6dab2c1",
                        "workflowitems":[

                        ],
                        "board":{
                           "id":"536e4d8e8ea442a8f6dab2b1",
                           "version":12,
                           "name":"MyBoard",
                           "workflowVerticalSizing":-1,
                           "showUserPictureEnabled":true
                        }
                     },
                     "parentWorkflow":{
                        "id":"536e4d8e8ea442a8f6dab2b0",
                        "board":{
                           "id":"536e4d8e8ea442a8f6dab2b1",
                           "version":12,
                           "name":"MyBoard",
                           "workflowVerticalSizing":-1,
                           "showUserPictureEnabled":true
                        }
                     },
                     "verticalSize":-1
                  }
               ],
               "board":{
                  "id":"536e4d8e8ea442a8f6dab2b1",
                  "version":12,
                  "name":"MyBoard",
                  "workflowVerticalSizing":-1,
                  "showUserPictureEnabled":true
               }
            },
            "showUserPictureEnabled":true,
            "tasks":[
               {
                  "id":"536f5bb88ea42ae6409864be",
                  "name":"task1",
                  "classOfService":{
                     "id":"536e4d328ea442a8f6dab2ac",
                     "name":"Standard",
                     "description":"For typical tasks that flow through the system.",
                     "colour":"5d8eef",
                     "version":1
                  },
                  "ticketId":"#5",
                  "workflowitemId":"536e4d928ea442a8f6dab2b6",
                  "version":2,
                  "projectId":"536e4d9c8ea442a8f6dab2c2",
                  "assignee":{
                     "userName":"admin",
                     "realName":"Default User",
                     "pictureUrl":"",
                     "sessionId":"",
                     "version":3
                  },
                  "order":"0",
                  "boardId":"536e4d8e8ea442a8f6dab2b1"
               },
               {
                  "id":"536f5d2d8ea42ae6409864c1",
                  "name":"task2",
                  "classOfService":{
                     "id":"536e4d328ea442a8f6dab2ac",
                     "name":"Standard",
                     "description":"For typical tasks that flow through the system.",
                     "colour":"5d8eef",
                     "version":1
                  },
                  "ticketId":"#6",
                  "workflowitemId":"536e4d928ea442a8f6dab2b6",
                  "version":4,
                  "projectId":"536e4d9c8ea442a8f6dab2c2",
                  "assignee":{
                     "userName":"x",
                     "realName":"x12",
                     "pictureUrl":"http://i.iinfo.cz/images/424/superpocitac-anselm-1-thumb.jpg",
                     "sessionId":"",
                     "version":5
                  },
                  "order":"100",
                  "boardId":"536e4d8e8ea442a8f6dab2b1"
               }
            ]
         },
         "projectsOnBoard":{
            "values":[
               {
                  "id":"536e4d9c8ea442a8f6dab2c2",
                  "name":"MyProject",
                  "boardIds":[
                     "536e4d8e8ea442a8f6dab2b1"
                  ],
                  "version":7
               }
            ]
         }
      }
   ]
}
```

As you can see the response is quite talky, but the structure of the JSON follows exactly the structure of the board (each board has a workflow, the workflow has workflowitems and each workflowitem can have nested workflow.)

OK, so now we had some fun, lets have a look at the details of all the available commands.

# Command Details #

The Kanbanik API is a rewrite of the old proprietary GWT-RPC API to an open JSON based one, but not really cleaned up. So you will see in lots of places arguments which are not needed for the command but are required (even not used at the end). This will be continually cleaned up, what you see is the first version.

## Task ##

### Properties of Task Entity ###
| Name | Description |
|:-----|:------------|
| id   | GUID of the task as stored in DB |
| name | Sort description |
| description | Long description |
| classOfService | Whole class of service entity |
| ticketId | Unique Id of this ticket (without the # sign) |
| workflowitemId | ID of the workflowitem on which this task is |
| version | Version for pessimistic locking |
| projectId | ID of the project on which this task is |
| assignee | Whole User entity |
| order | Order of the task relative to other tasks in one workflowitem |
| dueDate | The due date |
| boardId | ID of the board on which this task is|

### Task Related Commands ###
| **Command Name** | **Description** | **Arguments** | **Example** |
|:-----------------|:----------------|:--------------|:------------|
| **getTask**      | Returns a given task by ID. | The whole Task entity, but only the id is used - the rest is there for historical reasons | `http://localhost:4041/kanbanik/api?command={"commandName":"getTask","id":"536f5bb88ea42ae6409864be","name":"task1","workflowitemId":"536e4d928ea442a8f6dab2b6","projectId":"536e4d9c8ea442a8f6dab2c2","version":2,"boardId":"536e4d8e8ea442a8f6dab2b1","sessionId":"e63c51a1-c316-497e-bcfa-6f264e7714d1"} ` |
| **editTask**     | Modifyes the task | The whole Task entity | ` http://localhost:4041/kanbanik/api?command={"commandName":"editTask","id":"536f5bb88ea42ae6409864be","name":"task1","workflowitemId":"536e4d928ea442a8f6dab2b6","projectId":"536e4d9c8ea442a8f6dab2c2","version":3,"boardId":"536e4d8e8ea442a8f6dab2b1","sessionId":"e63c51a1-c316-497e-bcfa-6f264e7714d1","ticketId":"2"} `|
| **createTask**   | Creates a new task | The whole Task entity | ` http://localhost:4041/kanbanik/api?command={"commandName":"createTask","name":"task1","workflowitemId":"536e4d928ea442a8f6dab2b6","projectId":"536e4d9c8ea442a8f6dab2c2","version":1,"boardId":"536e4d8e8ea442a8f6dab2b1","sessionId":"e63c51a1-c316-497e-bcfa-6f264e7714d1","ticketId":"4","order":1} `|
| **deleteTask**   | Deletes the given list of tasks | The list of whole Task entity | ` http://localhost:4041/kanbanik/api?command= {"commandName":"deleteTask","sessionId":"e63c51a1-c316-497e-bcfa-6f264e7714d1","values":[{"id":"536f5bb88ea42ae6409864be","name":"task1","workflowitemId":"536e4d928ea442a8f6dab2b6","projectId":"536e4d9c8ea442a8f6dab2c2","version":4,"boardId":"536e4d8e8ea442a8f6dab2b1","sessionId":"e63c51a1-c316-497e-bcfa-6f264e7714d1","ticketId":"2"}]} `|
| **moveTask**     | Moves the task from one workflowitem to a different one | The whole Task entity with the filled target workflowitem and project. Also contains the optional arguments of the prevOrder and nextOrder - the orders of the previous item and next item in the target workflowitem. | ` http://localhost:4041/kanbanik/api?command={"commandName":"moveTask","sessionId":"e63c51a1-c316-497e-bcfa-6f264e7714d1","task":{"id":"536fb23e8ea433d19fca1eac","name":"task1","ticketId":"12","workflowitemId":"536e4d928ea442a8f6dab2b6","version":2,"projectId":"536e4d9c8ea442a8f6dab2c2","boardId":"536e4d8e8ea442a8f6dab2b1"},"prevOrder":"-99","nextOrder":"299.5"} ` |

## User ##
> TBD