package myaccount;

import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.DB;
import utility.Utility;

public class MyAccountDB extends DB {

	public MyAccountDB() throws SQLException {
		super();
	}
	
	public String getNameOfUser(int id) {
		String name = null;
		try {
            String sql = "SELECT NAME FROM users WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                name = result.getString("Name");
            }	            
        } 		
        catch (SQLException e) {
            //e.printStackTrace();
	    throw new MyAccountException("Error fetching user name", e);
        }	
		
		return name;    
	}
	
	public String getEmailOfUser(int id) {
		String email = null;
		try {
            String sql = "SELECT EMAIL FROM users WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                email = result.getString("Email");
            }	            
        } 		
        catch (SQLException e) {
            //e.printStackTrace();
		throw new MyAccountException("Error fetching user email", e);
        }
		
		return email;    
		
	}
	
	public String getDobOfUser(int id) {
		Date dob = null;
		try {
            String sql = "SELECT DOB FROM users WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                dob = result.getDate("DOB");
            }	            
        } 		
        catch (SQLException e) {
            //e.printStackTrace();
		throw new MyAccountException("Error fetching user date of birth", e);
        }
		
		return dob.toString();    
	}
	
	public static boolean checkPassword(int id, String password) {
		boolean correctPassword = false;
		String passwordFromDB = "";
		try {
            String sql = "SELECT password FROM users WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
            	passwordFromDB = result.getString("password");
            }	  
            
            if (password.equals(passwordFromDB)) {
            	correctPassword = true;
            }
        } 
		
        catch (SQLException e) {
            throw new MyAccountException("Error checking password", e);
            return correctPassword;	
        }
		
		return correctPassword;
	}
	
	public static void updatePassword(int id, String newPassword) {
		try {
	        String sql = "UPDATE users SET password = ? WHERE id = ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, newPassword);
	        statement.setInt(2, id);
	        int rowsUpdated = statement.executeUpdate();
	        if (rowsUpdated > 0) {
	            Utility.showAlert("Success", "Password update successfully!");
	        } 
	        
	        else {
	        	Utility.showAlert("Error", "Sorry. Password could not be updated.");	        
	        	
	        }
	    } 
		
		catch (SQLException e) {
	        //e.printStackTrace();
	        throw new MyAccountException("Sorry. Password could not be updated.", e);
	    }
	}

}
