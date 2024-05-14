package home;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import tasks.Task;
import utility.Utility;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import base.DB;
import base.SceneController;

import java.time.format.DateTimeFormatter;

public class HomeController extends SceneController{
	
    @FXML
    private Label dateDisplay;

    @FXML
    private Text helloText;

    @FXML
    private Label timeProductive;

    @FXML
    private Label todayDate;

    @FXML
    private ListView<String> toDoList;
    
    Date currentDateDB = Date.valueOf(LocalDate.now());
    
    LocalDate currentDate = LocalDate.now(); // Current date
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy"); //convert date to 03 May 2024 format
    String formattedDate = currentDate.format(formatter);
	
    //initialize method which displays custom "hello" message, the current date, minutes focused and tasks needed to be done today
	public void initialize() throws SQLException {		
		HomeDB db = new HomeDB();		
		helloText.setText("HELLO, " + db.getNameFromID(DB.getUserID()).toUpperCase() + "!");
		todayDate.setText(formattedDate);
		timeProductive.setText(String.valueOf(db.getFocusedTime(DB.getUserID(), currentDateDB)) + " minutes");
		
		List<Task> tasks = db.getTasks(DB.getUserID(), currentDateDB); // getting the task from database
		
		for (Task task: tasks) {
			toDoList.getItems().add(task.getTaskTitle()); //displaying only the task title			
		}
		
		displayBdayMessage(db); //calling the method to display happy birthday 
    }
	
	private void displayBdayMessage(HomeDB db) {

		LocalDate dob = db.getDobOfUser(DB.getUserID()); //get the birthday in LocalDate

		if (currentDate.getMonth() == dob.getMonth() && currentDate.getDayOfMonth() == dob.getDayOfMonth()) { // if date and month match
            Utility.showHappyBdayAlert(db.getNameFromID(DB.getUserID()).toUpperCase()); //show happy birthday alert
        }
	}
		
}
