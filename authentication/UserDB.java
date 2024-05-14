package authentication;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.DB;
import utility.Utility;

public class UserDB extends DB implements Authentication {

	public UserDB() throws SQLException {
		super();
	}

	@Override
	public boolean loginUser(String email, String password) {
		String sql = "SELECT email, password FROM users WHERE email = ? AND password = ?";
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            userSession(email, password);
            return result.next(); // if row is found, return true meaning successful login
        } 
        
        catch (Exception e) {
            return false; // return false indicating login failure
        }
	}
	
	@Override
	public boolean registerUser(String name, String email, String password, Date dob) {
		try {
			String sql = "INSERT INTO users (NAME,EMAIL,PASSWORD,DOB) VALUES (?, ?, ?, ?)";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, name);
	        statement.setString(2, email);
	        statement.setString(3, password);
	        statement.setDate(4, dob);

	        int result = statement.executeUpdate(); //will return the number of rows that are created
	        if (result > 0) { //if at least 1 row updated, means user has been added to database
	        	return true;
	        }	        
	        return false;
		}
		
		catch(Exception e) {
            return false; // return false -- registration failure
		}        
    }	

	@Override
	public void userSession(String email, String password) {
		try {
            String sql = "SELECT ID FROM users WHERE email = ? AND password = ?"; // gets ID corresponding to the user credentials
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int userID = result.getInt("ID");
                DB.setUserID(userID); // sets the userID attribute of DB class as the current userID for retrieval throughout the program
            }	            
        } 
            
        catch (SQLException e) { // if userID couldn't be set
            Utility.showAlert("Error", "Couldn't start user session."); 
        }
    }  	
	
	//method to check if the email exists in the database
	public boolean emailExists(String email) {
        try {
        	String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next(); 
            int count = resultSet.getInt(1);
            return count > 0; // if count > 0, email exists
        } 
        
        catch (SQLException e) {
            return false;
        }
    }
}

