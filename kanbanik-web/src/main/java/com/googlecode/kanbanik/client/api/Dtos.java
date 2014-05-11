package com.googlecode.kanbanik.client.api;

import java.util.List;

public class Dtos {

    public static interface BaseDto {
        String getCommandName();
        void setCommandName(String commandName);

        String getSessionId();
        void setSessionId(String sessionId);
    }

    public static interface LoginDto extends BaseDto {
        String getUserName();
        void setUserName(String userName);

        String getPassword();
        void setPassword(String password);
    }

    public static interface SessionDto extends BaseDto {
    }

    public static interface StatusDto {
        Boolean isSuccess();
        void setSuccess(Boolean success);

        void setReason(String reason);
        String getReason();
    }

    public static interface UserDto extends BaseDto {

        void setUserName(String userName);
        String getUserName();

        void setRealName(String realName);
        String getRealName();

        int getVersion();
        public void setVersion(int version);

        public String getPictureUrl();
        public void setPictureUrl(String pictureUrl);

        public String getSessionId();
        public void setSessionId(String sessionId);
    }

    public static interface UserManipulationDto extends UserDto, BaseDto {
        String getPassword();
        void setPassword(String password);

        String getNewPassword();
        void setNewPassword(String newPassword);
    }

    public static interface ClassOfServiceDto extends BaseDto {
        String getId();
        void setId(String id);

        String getName();
        void setName(String name);

        String getDescription();
        void setDescription(String description);

        String getColour();
        void setColour(String colour);

        int getVersion();
        void setVersion(int version);
    }

    public static interface ClassOfServicesDto {
        List<ClassOfServiceDto> getValues();
        void setValues(List<ClassOfServiceDto> values);
    }

    public static interface ProjectDto extends BaseDto {
        String getId();
        void setId(String id);

        String getName();
        void setName(String name);

        int getVersion();
        void setVersion(int version);

        List<String> getBoardIds();
        void setBoardIds(List<String> boardIds);
    }

    public static interface ProjectsDto {
        List<ProjectDto> getValues();
        void setValues(List<ProjectDto> values);
    }

    public static interface ProjectWithBoardDto extends BaseDto {
        void setProject(ProjectDto project);
        ProjectDto getProject();

        void setBoardId(String boardId);
        String getBoardId();
    }

    public static interface ErrorDto {
        String getErrorMessage();
        void setErrorMessage(String errorMessage);
    }

    public static interface UsersDto {
        List<UserDto> getValues();
        void setValues(List<UserDto> values);
    }

    public static interface TaskDto extends BaseDto {
        String getId();
        void setId(String id);

        String getName();
        void setName(String name);

        String getDescription();
        void setDescription(String description);

        ClassOfServiceDto getClassOfService();
        void setClassOfService(ClassOfServiceDto classOfService);

        String getTicketId();
        void setTicketId(String ticketId);

        String getWorkflowitemId();
        void setWorkflowitemId(String workflowitemId);

        int getVersion();
        void setVersion(int version);

        String getProjectId();
        void setProjectId(String projectId);

        UserDto getAssignee();
        void setAssignee(UserDto assignee);

        String getOrder();
        void setOrder(String order);

        String getDueDate();
        void setDueDate(String dueDate);

        String getBoardId();
        void setBoardId(String boardId);
    }

    public static interface TasksDto extends BaseDto {
        List<TaskDto> getValues();
        void setValues(List<TaskDto> values);
    }

    public static interface MoveTaskDto extends BaseDto {
        TaskDto getTask();
        void setTask(TaskDto task);

        String getPrevOrder();
        void setPrevOrder(String prevOrder);

        String getNextOrder();
        void setNextOrder(String nextOrder);
    }

    public static interface WorkflowitemDto extends BaseDto {
        String getName();
        void setName(String name);

        String getId();
        void setId(String id);

        int getWipLimit();
        void setWipLimit(int wipLimit);

        String getItemType();
        void setItemType(String itemType);

        int getVersion();
        void setVersion(int version);

        WorkflowDto getNestedWorkflow();
        void setNestedWorkflow(WorkflowDto nestedWorkflow);

        WorkflowDto getParentWorkflow();
        void setParentWorkflow(WorkflowDto parentWorkflow);

        int getVerticalSize();
        void setVerticalSize(int verticalSize);
    }

    public static enum ItemType {
        HORIZONTAL("H"),
        VERTICAL("V");

        private String type;
        private ItemType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

       public static ItemType from(String type) {
           if ("H".equals(type)) {
               return HORIZONTAL;
           }

           if ("V".equals(type)) {
               return VERTICAL;
           }

           return HORIZONTAL;
       }

    }

    public static interface WorkflowDto extends BaseDto {
        String getId();
        void setId(String id);

        List<WorkflowitemDto> getWorkflowitems();
        void setWorkflowitems(List<WorkflowitemDto> workflowitems);

        BoardDto getBoard();
        void setBoard(BoardDto board);
    }

    public static interface BoardDto extends BaseDto {
        void setName(String name);
        String getName();

        String getId();
        void setId(String id);

        int getVersion();
        void setVersion(int version);

        int getWorkflowVerticalSizing();
        void setWorkflowVerticalSizing(int workflowVerticalSizing);

        WorkflowDto getWorkflow();
        void setWorkflow(WorkflowDto workflow);

        List<TaskDto> getTasks();
        void setTasks(List<TaskDto> tasks);

        boolean isShowUserPictureEnabled();
        void setShowUserPictureEnabled(boolean showUserPictureEnabled);
    }

    public static interface BoardWithProjectsDto extends BaseDto {
        void setBoard(BoardDto board);
        BoardDto getBoard();

        void setProjectsOnBoard(ProjectsDto projectsOnBoard);
        ProjectsDto getProjectsOnBoard();

    }

    public static interface BoardsWithProjectsDto extends BaseDto {
        List<BoardWithProjectsDto> getValues();
        void setValues(List<BoardWithProjectsDto> values);
    }

    public static interface GetAllBoardsWithProjectsDto extends BaseDto {
        void setIncludeTasks(Boolean includeTasks);
        Boolean isIncludeTasks();
    }

    public static interface EditWorkflowParams extends BaseDto {
        void setCurrent(WorkflowitemDto current);
        WorkflowitemDto getCurrent();

        void setNext(WorkflowitemDto next);
        WorkflowitemDto getNext();

        void setDestinationWorkflow(WorkflowDto next);
        WorkflowDto getDestinationWorkflow();

        void setBoard(BoardDto board);
        BoardDto getBoard();

    }

    public static enum WorkflowVerticalSizing {
        BALANCED(-1),
        MIN_POSSIBLE(1);

        private int sizing;

        private WorkflowVerticalSizing(int sizing) {
            this.sizing = sizing;
        }

        public int getSizing() {
            return sizing;
        }

        public static WorkflowVerticalSizing from(int i) {
            if (i == 0 || i == -1) {
                return BALANCED;
            }

            if (i == 1) {
                return MIN_POSSIBLE;
            }

            // default
            return BALANCED;
        }
    }

    public static interface EmptyDto {

    }

}