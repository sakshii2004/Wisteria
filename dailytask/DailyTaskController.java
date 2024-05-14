package dailytask;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import base.DB;
import base.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tasks.Task;
import utility.Utility;

public class DailyTaskController extends SceneController {

    @FXML
    private Label dateDisplay;

    @FXML
    private DatePicker taskDate;

    @FXML
    private TextArea taskDesc;

    @FXML
    private ChoiceBox<String> taskPriority;

    @FXML
    private TextField taskTitle;
    
    @FXML
    private TableView<Task> tableToDo;
    
    @FXML
    private TableColumn<Task, String> taskNameColumn;
    
    @FXML
    private TableColumn<Task, String> priorityColumn;
    
    DailyTaskDB db;
    
    Date currentDate = Date.valueOf(LocalDate.now());
    Date focusDate = Date.valueOf(LocalDate.now()); //default value of focusDate is current date
    
    public void setSelectedDate(Date dateFocus) throws SQLException { // this method is called in the calendar controller to set the focus date as the selected date on the calendar
        this.focusDate = dateFocus;
        initialize(focusDate); 
    } 
    
    
    public void initialize() throws SQLException {
    	taskPriority.setItems(FXCollections.observableArrayList("Low", "Medium", "High"));    	
    	dateDisplay.setText(currentDate.toString());
    	
    	try {
    		//get all the tasks pertaining to that day
			db = new DailyTaskDB();
			List<Task> tasks = db.getTasks(DB.getUserID(), currentDate);
			//set the columns of the table accordingly
			taskNameColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("taskTitle"));
			priorityColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("taskPriority"));
		    ObservableList<Task> observableTasks = FXCollections.observableArrayList(tasks);
		    tableToDo.setItems(observableTasks);
		} 
		
		catch (SQLException e) {
			Utility.showAlert("Error", "Sorry, tasks couldn't be updated :(");
		}   	
    }
    
    public void initialize(Date customDate) throws SQLException {
    	taskPriority.setItems(FXCollections.observableArrayList("Low", "Medium", "High"));    	
    	dateDisplay.setText(customDate.toString());
    	
    	try { 
    		//get all the tasks pertaining to that day
			db = new DailyTaskDB();
			List<Task> tasks = db.getTasks(DB.getUserID(), customDate);
			//set the columns of the table accordingly
			taskNameColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("taskTitle"));
			priorityColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("taskPriority"));
		    ObservableList<Task> observableTasks = FXCollections.observableArrayList(tasks);
		    tableToDo.setItems(observableTasks);	
		} 
		
		catch (SQLException e) {
			Utility.showAlert("Error", "Sorry, tasks couldn't be updated :(");
		}   	
    }
    

    @FXML
    void addTask(ActionEvent event) {
    	// get the task information
    	String title = taskTitle.getText();
    	String desc = taskDesc.getText();
    	String priority = taskPriority.getValue();
    	
    	if (title == null || desc == null || priority == null) {
    		Utility.showAlert("Error", "Please fill all the fields.");
    	}
    	
    	Date inputDate = Date.valueOf(LocalDate.parse(dateDisplay.getText())); //get date displayed on the screen    	
    	LocalDate date = taskDate.getValue(); //getting the date from the taskDate field (will be null if user doesn't select any date)
    	if (date != null) { //if user has selected a date (taskDate is not null)
    		inputDate = Date.valueOf(date); //then use that date only
    	}
    	
		try {
			db = new DailyTaskDB();
			db.createTask(title,  desc, priority, DB.getUserID(), inputDate);
			Utility.showAlert("Success!", "Task created successfully!"); //success alert if task is created
			
			try {
				super.handleTodoButton(event); //reload the daily task or to-do page when task is entered
			} 
			
			catch (IOException e) {
				Utility.showAlert("Error!", "Could not reload page.");
			}
	    	
		} 
		
		catch (SQLException e) { 
			Utility.showAlert("Error!", "Task could not be created :("); //failure alert when task couldn't be added in database
		}   	
	}
    
    
    // method associated with the go to previous day button
    @FXML
    void goToPreviousDay(ActionEvent event) {
        LocalDate currentDate = LocalDate.parse(dateDisplay.getText()); //get the date displayed
        LocalDate previousDate = currentDate.minusDays(1); //go back 1 day
        dateDisplay.setText(previousDate.toString());
        Date previousDateAsDate = Date.valueOf(previousDate);

        try {
            initialize(previousDateAsDate); //run the initialize method with the previous date
        } 
        
        catch (SQLException e) {
        	Utility.showAlert("Error", "Could not show tasks of previous day.");
        }
    }
   
 // method associated with the go to next day button
    @FXML
    void goToNextDay(ActionEvent event) {
    	LocalDate currentDate = LocalDate.parse(dateDisplay.getText()); //get the date displayed
        LocalDate previousDate = currentDate.plusDays(1); //go forward 1 day
        dateDisplay.setText(previousDate.toString());
        Date previousDateAsDate = Date.valueOf(previousDate);

        try {
            initialize(previousDateAsDate); //run the initialize method with the previous date
        } 
        
        catch (SQLException e) {
        	Utility.showAlert("Error", "Could not show tasks of next day.");
        }
    }
    
    // method to delete task
    @FXML
    void deleteTask(ActionEvent event) {
    	Task selectedTask = tableToDo.getSelectionModel().getSelectedItem(); // get selected task object
    	if (selectedTask != null) {
    		Alert alert = new Alert(Alert.AlertType.CONFIRMATION); // confirming from the user
            alert.setTitle("Confirm Delete Task");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this task?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                	db = new DailyTaskDB();
                    db.deleteTask(selectedTask);
                    tableToDo.getItems().remove(selectedTask); // Remove the task from the TableView
                } 
                
                catch (SQLException e) {
                    Utility.showAlert("Error", "Could not delete task.");
                }
            }
        } 
        
        else {
            // No task selected, show an error message 
        	Utility.showAlert("Error", "Please select a task to delete.");
        }
    
    }
    
    
    // method to view task
    @FXML
    public void viewTask(ActionEvent event) {
    	Task selectedTask = tableToDo.getSelectionModel().getSelectedItem(); // get selected task
    	if (selectedTask != null) {
    		Alert alert = new Alert(Alert.AlertType.INFORMATION); //show task information in the alert box
    		alert.setTitle("View Task");  
    		alert.setHeaderText(selectedTask.getTaskTitle());
    		alert.setContentText("Priority: " + selectedTask.getTaskPriority() + "\n" + "Description: " + selectedTask.getTaskDesc());
    		alert.showAndWait();
    	}
    	else {
            // No task selected, show an error message 
        	Utility.showAlert("Error", "Please select a task to view.");
        }
    }
}
   
