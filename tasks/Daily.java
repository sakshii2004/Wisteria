package tasks;

public class Daily extends Task { //class for Daily Task Object
	
	public String typeOfTask;
	
	public Daily(int taskID, String taskTitle, String taskDesc, String taskPriority) {
		super(taskID, taskTitle, taskDesc, taskPriority);
		this.typeOfTask = "Daily";		
	}
}
