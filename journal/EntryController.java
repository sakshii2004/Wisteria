package journal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import utility.Utility;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import base.SceneController;

public class EntryController extends SceneController {

    @FXML
    private TextField subjectField;

    @FXML
    private TextArea entryArea;
    
    @FXML
    public void handleSaveButton(ActionEvent event) {
    	LocalDate date = LocalDate.now();
        String subject = subjectField.getText();
        String entryText = entryArea.getText();

        if (subject.isEmpty() || entryText.isEmpty()) {
            Utility.showAlert("Error", "Please fill in all fields!");
            return;
        }

        JournalEntry journalEntry = new JournalEntry(date, subject, entryText);
        try {
            JournalDB.addEntry(journalEntry);
            try {
				showHome(entryArea.getScene().getWindow());
			} 
            
            catch (IOException e) {				
				//e.printStackTrace();
            	Utility.showAlert("Error", "Failed to load homepage. Window might have to be closed.");
            	
			}
        } 
        
        catch (SQLException e) {
            //e.printStackTrace();
            Utility.showAlert("Error", "Failed to save entry. Please try again later.");
        }
    }

    @FXML
    public void handleCancelButton(ActionEvent event) {
    	try {               	
			// Load the home.fxml file
        	showHome(subjectField.getScene().getWindow());
        } 
        
        catch (IOException e) {
        	Utility.showAlert("Error Message", "Can't load homepage"); // display alert if home can't be loaded 
        }
    }
}

