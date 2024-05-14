package tasks;

public class Task {
	private int taskID;
	private String taskTitle;
	private String taskDesc;
	private String taskPriority;
	
	public Task(int taskID, String taskTitle, String taskDesc, String taskPriority) {
		this.taskID = taskID;
		this.taskTitle = taskTitle;
		this.taskDesc = taskDesc;
		this.taskPriority = taskPriority;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public String getTaskPriority() {
		return taskPriority;
	}

	public void setTaskPriority(String taskPriority) {
		this.taskPriority = taskPriority;
	}
	
	
}

