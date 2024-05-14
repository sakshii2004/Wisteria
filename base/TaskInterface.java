package base;

import java.sql.Date;
import java.util.List;
import tasks.Task;

public interface TaskInterface { // interface for CRUD operations for the tasks (daily and monthly)
	
	public void deleteTask(Task task);

	void createTask(String taskTitle, String taskDesc, String taskPriority, int userID, Date date);

	List<Task> getTasks(int id, Date date);
}
