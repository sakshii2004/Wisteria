package tasks;

public class Monthly extends Task { //class for Monthly Task Object
	
	public String typeOfTask;
	
	public Monthly(int taskID, String taskTitle, String taskDesc, String taskPriority) {
		super(taskID, taskTitle, taskDesc, taskPriority);
		this.typeOfTask = "Monthly";		
	}
}