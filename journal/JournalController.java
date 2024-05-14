package journal;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utility.Utility;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import base.DB;
import base.SceneController;

public class JournalController extends SceneController {

    @FXML
    private ListView<String> entryListView;
    
    @FXML
    private TableView<JournalEntry> tableJournalEntries;

    @FXML
    private TableColumn<JournalEntry, String> subjectColumn;

    @FXML
    private TableColumn<JournalEntry, LocalDate> dateColumn;

    @FXML
    private TableColumn<JournalEntry, String> entryColumn;

    JournalDB db;
    
    public void initialize() {
    	try {
    		db = new JournalDB();
    		List<JournalEntry> entries = db.getAllEntries(DB.getUserID());
    		dateColumn.setCellValueFactory(new PropertyValueFactory<JournalEntry, LocalDate>("date"));
    		subjectColumn.setCellValueFactory(new PropertyValueFactory<JournalEntry, String>("subject"));
    		entryColumn.setCellValueFactory(new PropertyValueFactory<JournalEntry, String>("entry"));
    		
    		ObservableList<JournalEntry> observableEntries = FXCollections.observableArrayList(entries);
    		
    		tableJournalEntries.setItems(observableEntries);    		
    	}
    	
    	catch(SQLException e) {
    		Utility.showAlert("Error","Journal Entries could not be loaded.");
			//e.printStackTrace();
    	}
    }
    
    @FXML
    public void handleDeleteButton(ActionEvent event) {
        JournalEntry selectedEntry = tableJournalEntries.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this entry?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    JournalDB.deleteEntry(selectedEntry);
                    tableJournalEntries.getItems().remove(selectedEntry); // Remove the entry from the TableView
                } 
                
                catch (SQLException e) {
                    //e.printStackTrace();
                	Utility.showAlert("Error", "Could not delete entry, try again later.");
                }
            }
        } 
        
        else {
            Utility.showAlert("Error","Please select an entry to delete.");
        }
    }

    @FXML
    public void handleAddButton(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/journal/addentry.fxml"));
        stage.setTitle("New Journal Entry");
        stage.setScene(new Scene(root));
        stage.showAndWait();
        initialize();
    }
    
    @FXML
    public void handleViewButton(ActionEvent event) {
        JournalEntry selectedJournalEntry = tableJournalEntries.getSelectionModel().getSelectedItem();
        if (selectedJournalEntry != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/journal/viewjournal.fxml"));
                Parent root = loader.load();
                ViewJournalEntryController controller = loader.getController();
                controller.setJournalEntry(selectedJournalEntry); // pass the selected journal entry to the controller
                //the controller sets the fields in the setJournalEntry method in the controller for viewjournal.fxml
                Stage stage = new Stage();
                stage.setTitle("View Journal Entry");
                stage.setScene(new Scene(root));
                stage.show();
            } 
            
            catch (IOException e) {
                //e.printStackTrace();
            	Utility.showAlert("Error","Could not load the entries view, try again later.");
            }
        }
        else {
        	Utility.showAlert("Error","Please select an entry to view.");
        }
    }
}

