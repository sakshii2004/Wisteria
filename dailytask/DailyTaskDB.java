package dailytask;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import base.DB;
import base.TaskInterface;
import tasks.Daily;
import tasks.Task;

public class DailyTaskDB extends DB implements TaskInterface  {

	public DailyTaskDB() throws SQLException {
		super();
	}

	// method to retrieve tasks from the database
	@Override
	public List<Task> getTasks(int id, Date date) {
		List<Task> tasks = new ArrayList<>();
		String sql = "SELECT * FROM dailytask WHERE userID = ? AND dateOfTask = ?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);		
			preparedStatement.setInt(1, id); 
	        preparedStatement.setDate(2, date); 
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        while (resultSet.next()) {
	        	int taskID = resultSet.getInt("taskID");
	        	String taskTitle = resultSet.getString("taskTitle");
	        	String taskDesc = resultSet.getString("taskDesc");
	            String taskPriority = resultSet.getString("taskPriority");
	        	Task task = new Daily(taskID, taskTitle, taskDesc, taskPriority);
	        	tasks.add(task);
	        }
	        
	        return tasks;
		}
	    
	    catch(Exception e) {
	    	//e.printStackTrace();
	    	return tasks;
	    }
	}

	// method to insert task in the database
	@Override
	public void createTask(String taskTitle, String taskDesc, String taskPriority, int userID, Date date) {
	   
	    String sql = "INSERT INTO dailytask (taskTitle, taskDesc, taskPriority, userID, dateOfTask) VALUES (?, ?, ?, ?, ?)";
	    try {
	    	PreparedStatement statement = con.prepareStatement(sql);
		    statement.setString(1, taskTitle);
	        statement.setString(2, taskDesc);
	        statement.setString(3, taskPriority);
	        statement.setInt(4, userID);
	        statement.setDate(5, date);
	        statement.executeUpdate();
	    }
	    
	    catch (Exception e) {
	    	//e.printStackTrace();
	    	return;
	    }		
	}

	// method to delete task from the database
	@Override
	public void deleteTask(Task task) {
		String query = "DELETE FROM dailyTask WHERE taskTitle = ? AND taskPriority = ? AND userID = ?";
        
        try {
        	 PreparedStatement statement = con.prepareStatement(query);
             statement.setString(1, task.getTaskTitle());
             statement.setString(2, task.getTaskPriority());
             statement.setInt(3, DB.getUserID());
             statement.executeUpdate();      	
        }
        
        catch(Exception e) {
        	//e.printStackTrace();
        	return;
        }
		
	}

}
