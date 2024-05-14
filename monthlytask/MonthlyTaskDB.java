package monthlytask;

import java.sql.Date;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import base.DB;
import base.TaskInterface;
import tasks.Monthly;
import tasks.Task;
import utility.Utility;

public class MonthlyTaskDB extends DB implements TaskInterface {

	public MonthlyTaskDB() throws SQLException {
		super();
		// code
	}

	@Override
	public void deleteTask(Task task) {
		String query = "DELETE FROM monthlytask WHERE taskTitle = ? AND taskPriority = ? AND userID = ?";
        
        try {
        	 PreparedStatement statement = con.prepareStatement(query);
             statement.setString(1, task.getTaskTitle());
             statement.setString(2, task.getTaskPriority());
             statement.setInt(3, DB.getUserID());
             statement.executeUpdate();      	
        }
        
        catch(Exception e) {
        	e.printStackTrace();
        }
		
	}

	@Override
	public void createTask(String taskTitle, String taskDesc, String taskPriority, int userID, Date date) {
		LocalDate monthYear = date.toLocalDate();
		int year = monthYear.getYear();
		Month month = monthYear.getMonth();
		int monthNumber = month.getValue();
		
		String sql = "INSERT INTO monthlytask (taskTitle, taskDesc, taskPriority, monthOfTask, yearOfTask, userID) VALUES (?, ?, ?, ?, ?, ?)";
		try {
	    	PreparedStatement statement = con.prepareStatement(sql);
		    statement.setString(1, taskTitle);
	        statement.setString(2, taskDesc);
	        statement.setString(3, taskPriority);
	        statement.setInt(4, monthNumber);
	        statement.setInt(5, year);
	        statement.setInt(6, userID);
	        statement.executeUpdate();
	    }
	    
	    catch (Exception e) {
	    	e.printStackTrace();
	    }	
		
		
	}
	
	

	@Override
	public List<Task> getTasks(int id, Date date) {
		List<Task> tasks = new ArrayList<>(); //Empty list
		int monthNumber = Utility.getMonth(date);
		int year = Utility.getYear(date);
		
		String sql = "SELECT * FROM monthlytask WHERE userID = ? AND monthOfTask = ? AND yearOfTask = ?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);		
			preparedStatement.setInt(1, id); 
	        preparedStatement.setInt(2, monthNumber); 
	        preparedStatement.setInt(3, year); 
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        while (resultSet.next()) {
	        	int taskID = resultSet.getInt("taskID");
	        	String taskTitle = resultSet.getString("taskTitle");
	        	String taskDesc = resultSet.getString("taskDesc");
	            String taskPriority = resultSet.getString("taskPriority");
	        	Task task = new Monthly(taskID, taskTitle, taskDesc, taskPriority);
	        	tasks.add(task);
	        }	        
	        return tasks;
		}
	    
	    catch(Exception e) {
	    	e.printStackTrace();
	    	return tasks;
	    }
	}

}

