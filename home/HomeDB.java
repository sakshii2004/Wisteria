package home;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import base.DB;
import tasks.Daily;
import tasks.Task;
import utility.Utility;

public class HomeDB extends DB {
	
	public HomeDB() throws SQLException {
		super();
	}
	
	// method to get the name of the user from database
	public String getNameFromID(int ID) {
		String name = "User";
		String sql = "SELECT name FROM users WHERE ID = ?";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, ID);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				name = resultSet.getString("name");
			}
			return name;
		}
		
		catch (Exception e) {
            return name; 
        }
		
	}
	
	public int getFocusedTime(int ID, Date date) {
		
		String sql = "SELECT SUM(timeLimit) AS total_time FROM timedtasks  WHERE UserID = ? AND dateOfTask = ?";

		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, ID);
			statement.setDate(2, date);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
	            int totalTime = resultSet.getInt("total_time");
	            return totalTime;
	        } 
			
			else {
	            return 0; //if no records are found
	        }	
		}
		
		catch (Exception e) {
            e.printStackTrace(); 
            return 0; 
        }
	}
	
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
	    	Utility.showAlert("Error", "Could not fetch tasks from database.");
	    	return tasks;
	    }
	}
	
	public LocalDate getDobOfUser(int id) {
		LocalDate dob = null;
		try {
            String sql = "SELECT DOB FROM users WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                dob = result.getDate("DOB").toLocalDate();;
            }	            
        } 		
        catch (SQLException e) {
            //e.printStackTrace();
        }		
		return dob;   
	}

}
