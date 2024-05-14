package monthlytask;

import java.io.IOException;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import base.DB;
import base.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tasks.Task;
import utility.Utility;

public class MonthlyTaskController extends SceneController {

    @FXML
    private Button addGoalButton;

    @FXML
    private Button calendarButton;

    @FXML
    private TextArea goalDesc;

    @FXML
    private ChoiceBox<String> goalMonth;

    @FXML
    private ChoiceBox<String> goalPriority;

    @FXML
    private TextField goalTitle;

    @FXML
    private ChoiceBox<Integer> goalYear;

    @FXML
    private Button goalsButton;

    @FXML
    private Button habitTrackerButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button journalEntriesButton;

    @FXML
    private Label monthYearDisplay;

    @FXML
    private Button nextMonth;

    @FXML
    private Button previousMonth;
    
    @FXML
    private TableColumn<Task, String> goalNameColumn;

    @FXML
    private TableColumn<Task, String> priorityColumn;

    @FXML
    private TableView<Task> tableMonthlyGoals;

    @FXML
    private Button timerButton;

    @FXML
    private Button toDoListButton;

    @FXML
    private Button weeklyPlannerButton;    

    @FXML
    private Button deleteTaskButton;
    
    @FXML
    private Button viewTaskButton;
    
    
    MonthlyTaskDB db; 
    
    Date currentDate = Date.valueOf(LocalDate.now());
    
    public void initialize() throws SQLException {
    	goalPriority.setItems(FXCollections.observableArrayList("Low", "Medium", "High"));
    	goalMonth.setItems(FXCollections.observableArrayList(Utility.monthNames));
    	goalYear.setItems(FXCollections.observableArrayList(Utility.getYears()));
    	
    	
    	LocalDate localDate = currentDate.toLocalDate();
        Month month = localDate.getMonth();
        String monthName = month.toString();
        int year = localDate.getYear();
        
        monthYearDisplay.setText(monthName + " " + year);  
        
        Date monthYear = Utility.getDate(monthYearDisplay.getText()); 
        
        try {        	
        	db = new MonthlyTaskDB();
        	List<Task> monthlyTasks = db.getTasks(DB.getUserID(), monthYear); 
        	goalNameColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("taskTitle"));
			priorityColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("taskPriority"));
		    ObservableList<Task> observableTasks = FXCollections.observableArrayList(monthlyTasks);
		    tableMonthlyGoals.setItems(observableTasks);
        }
        
        catch (SQLException e) {
        	Utility.showAlert("Error", "Sorry, goals couldn't be loaded :(");
			//e.printStackTrace();
		}	
    }
    
    public void initialize(Date customMonthYear) throws SQLException {
    	goalPriority.setItems(FXCollections.observableArrayList("Low", "Medium", "High"));
    	goalMonth.setItems(FXCollections.observableArrayList(Utility.monthNames));
    	goalYear.setItems(FXCollections.observableArrayList(Utility.getYears()));
    	
    	
    	LocalDate localDate = customMonthYear.toLocalDate();
        Month month = localDate.getMonth();
        String monthName = month.toString();
        int year = localDate.getYear();
        
        monthYearDisplay.setText(monthName + " " + year);  
        
        Date monthYear = Utility.getDate(monthYearDisplay.getText()); 
        
        try {        	
        	db = new MonthlyTaskDB();
        	List<Task> monthlyTasks = db.getTasks(DB.getUserID(), monthYear); 
        	goalNameColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("taskTitle"));
			priorityColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("taskPriority"));
		    ObservableList<Task> observableTasks = FXCollections.observableArrayList(monthlyTasks);
		    tableMonthlyGoals.setItems(observableTasks);
        }
        
        catch (SQLException e) {
        	Utility.showAlert("Error", "Sorry, goals couldn't be loaded :(");
			//e.printStackTrace();
		}	
    }
    
    
    

    @FXML
    void addGoal(ActionEvent event) {
    	String title = goalTitle.getText();
    	String desc = goalDesc.getText();
    	String priority = goalPriority.getValue();    	
    	String monthName = goalMonth.getValue();
    	
    	if (title == null || desc == null || priority == null) {
    		Utility.showAlert("Alert", "Please fill in the title, description and priority.");
    		return;
    	}
    	
    	int monthNumber;
    	
    	if (monthName == null) { //if no month is selected, consider month number as 0
    		monthNumber = 0;
    	}    	
    	else { //if month is selected, take the value in int
    		Month monthEnum = Month.valueOf(goalMonth.getValue());
    		monthNumber = monthEnum.getValue();
    	}
    	
    	Integer year = goalYear.getValue();
    	
    	if (monthNumber == 0) { //if user selects no month, then take current month
    		LocalDate localDate = currentDate.toLocalDate();
            Month month = localDate.getMonth(); //get current month
            monthNumber = month.getValue(); //get the value of the month number
    	}
    	
    	if (year == null) { //if user selects no year, then take current year
    		LocalDate localDate = currentDate.toLocalDate();
    		year = localDate.getYear();
    	}
    	
    	LocalDate localDate = LocalDate.of(year, monthNumber, 1); // day is always 01, we care only about the month and year
    	Date monthYear = Date.valueOf(localDate);
    	
    	try {
			db = new MonthlyTaskDB();
			db.createTask(title,  desc, priority, DB.getUserID(), monthYear);
			Utility.showAlert("Success!", "Goal created successfully!");
			
			try {
				super.handleGoalsButton(event);
			} 
			
			catch (IOException e) {
				Utility.showAlert("Error", "Sorry, goals couldn't be loaded :(");
				//e.printStackTrace();
			}
	    	
		} 
		
		catch (SQLException e) {
			Utility.showAlert("Error", "Sorry, goals couldn't be added to database. :(");
			//e.printStackTrace();
		}	
    	


    }

    @FXML
    void goToPreviousMonth(ActionEvent event) {
    	Date monthYear = Utility.getDate(monthYearDisplay.getText());
    	LocalDate previousMonth = monthYear.toLocalDate().minusMonths(1);
    	monthYear = Date.valueOf(previousMonth);
    	try {
			initialize(monthYear);
		} 
    	
    	catch (SQLException e) {
    		Utility.showAlert("Error", "Sorry, goals couldn't be loaded :(");
			//e.printStackTrace();
		} 
    }

    @FXML
    void goToNextMonth(ActionEvent event) {
    	Date monthYear = Utility.getDate(monthYearDisplay.getText());
    	LocalDate previousMonth = monthYear.toLocalDate().plusMonths(1);
    	monthYear = Date.valueOf(previousMonth);
    	try {
			initialize(monthYear);
		} 
    	
    	catch (SQLException e) {
			//e.printStackTrace();
    		Utility.showAlert("Error", "Sorry, goals couldn't be loaded :(");
		} 
    }
    
    @FXML
    void deleteTask(ActionEvent event) {
    	Task selectedTask = tableMonthlyGoals.getSelectionModel().getSelectedItem();
    	if (selectedTask != null) {
    		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete Goal");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this goal?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                	db = new MonthlyTaskDB();
                    db.deleteTask(selectedTask);
                    tableMonthlyGoals.getItems().remove(selectedTask); // Remove the task from the TableView
                } 
                
                catch (SQLException e) {
                    //e.printStackTrace();
                	Utility.showAlert("Error", "Sorry, goals couldn't be deleted :(");
                }
            }
        } 
        
        else {
            // No task selected, show an error message or handle it accordingly
        	Utility.showAlert("Error", "Please select a goal to delete.");
        }
    
    }
    
    @FXML
    public void viewTask(ActionEvent event) {
    	Task selectedTask = tableMonthlyGoals.getSelectionModel().getSelectedItem();
    	if (selectedTask != null) {
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setTitle("View Task");
    		alert.setHeaderText(selectedTask.getTaskTitle());
    		alert.setContentText("Priority: " + selectedTask.getTaskPriority() + "\n" + "Description: " + selectedTask.getTaskDesc());
    		alert.showAndWait();
    		
    	}
    	else {
    		Utility.showAlert("Error","Please select a goal to view.");
    	}
    }
}
