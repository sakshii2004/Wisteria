package timer;

import java.sql.*;
import java.time.LocalDate;

import base.DB;
import utility.Utility;

public class TaskDB extends DB {
	
    public TaskDB() throws SQLException {
        super();
    }

    public void insertTimedTask(TimedTask task) {
        try {
            String sql = "INSERT INTO timedtasks(taskName, timeLimit, UserID, dateOfTask) VALUES(?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, task.getTaskName());
            statement.setInt(2, task.getTimeLimit());
            statement.setInt(3, DB.getUserID());
            statement.setDate(4, Date.valueOf(LocalDate.now()));
            statement.executeUpdate();
        } 
        catch (SQLException e) { 
            //e.printStackTrace();
        	Utility.showAlert("Error", "Could not add timed task");
        }
    }
}
