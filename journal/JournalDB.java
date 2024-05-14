package journal;

import java.sql.*;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import base.DB;
import utility.Utility;

public class JournalDB extends DB {
    
	public JournalDB() throws SQLException {
		super();
	}

    public static void addEntry(JournalEntry entry) throws SQLException {
        String query = "INSERT INTO entries (date, subject, entry, userID) VALUES (?, ?, ?, ?)";
        try {
        	PreparedStatement statement = con.prepareStatement(query);
            statement.setDate(1, Date.valueOf(entry.getDate()));
            statement.setString(2, entry.getSubject());
            statement.setString(3, entry.getEntry());
            statement.setInt(4, DB.getUserID());
            statement.executeUpdate();
        }
        
        catch(Exception e) {
        	//e.printStackTrace();
        	Utility.showAlert("Error", "Could not add entry, try again later");
        }
    }

    public List<JournalEntry> getAllEntries(int id) throws SQLException {
        List<JournalEntry> entries = new ArrayList<>();
        String query = "SELECT * FROM entries WHERE UserID = ? ORDER BY date DESC";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Date date = resultSet.getDate("date");
                String subject = resultSet.getString("subject");
                String entry = resultSet.getString("entry");
                if (date != null) {
                    entries.add(new JournalEntry(date.toLocalDate(), subject, entry));
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        	Utility.showAlert("Error", "Could not add entries, try again later");
        }
        return entries;
    }

    public static void deleteEntry(JournalEntry entry) throws SQLException {
        String query = "DELETE FROM entries WHERE date = ? AND subject = ? AND userID = ?";
        
        try {
        	 PreparedStatement statement = con.prepareStatement(query);
             statement.setDate(1, Date.valueOf(entry.getDate()));
             statement.setString(2, entry.getSubject());
             statement.setInt(3, DB.getUserID());
             statement.executeUpdate();      	
        }
        
        catch(Exception e) {
        	//e.printStackTrace();
        	Utility.showAlert("Error", "Could not delete entry, try again later");
        }

    }
    
    public static String getEntry(JournalEntry entry) throws SQLException {
        String query = "SELECT entry FROM entries WHERE date = ? AND subject = ?";
        
        try {
        	PreparedStatement statement = con.prepareStatement(query);
        	statement.setDate(1, Date.valueOf(entry.getDate()));
        	statement.setString(2, entry.getSubject());
        	ResultSet resultSet = statement.executeQuery();
        	if (resultSet.next()) {
                return resultSet.getString("entry");
            }
        	return null;
        }
        
        catch(Exception e) {
        	//e.printStackTrace();
        	Utility.showAlert("Error", "Could not get entry, try again later");
        	return null;
        }
    }
}
