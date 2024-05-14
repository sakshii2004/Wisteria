package base;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;
import timer.TimerController;

// parent class of all controller classes
public class SceneController { 
	
	// method to show home when object of type ActionEvent is passed as parameter
	public void showHome(ActionEvent event) throws IOException {
	    Window window = ((javafx.scene.Node) event.getSource()).getScene().getWindow();
	    showHome(window);
	}
	
	// method to show home when object of type Window is passed as parameter -- eg: after logging in
	public void showHome(Window window) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/home/home.fxml"));
        Stage stage = (Stage) window;
        Scene scene = new Scene(root);
        stage.setTitle("Wisteria");
		Image icon = new Image("icon.png");
		stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
	
	// method associated with the hyperlink on the register page	
	@FXML
    public void switchToLogin(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/authentication/login.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setTitle("Wisteria");
		Image icon = new Image("icon.png");
		stage.getIcons().add(icon);
    	stage.setResizable(false);
    	stage.setScene(scene);
    	stage.show();
    }  
	
	// method associated with the hyperlink on the login page
	public void switchToRegister(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/authentication/registration.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setTitle("Wisteria");
		Image icon = new Image("icon.png");
		stage.getIcons().add(icon);
    	stage.setResizable(false);
    	stage.setScene(scene);
    	stage.show();
    }
	
	// method associated with the button for timer 
	@FXML
	public void handleTimerButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/timer/timer.fxml"));
        Parent root = loader.load();
        TimerController timerController = loader.getController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Wisteria");
        Image icon = new Image("icon.png");
		stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        timerController.refreshUI();
    }
	
	// method associated with the button for calendar 
	@FXML
	public void handleCalendarButton(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/calendar/calendar.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Wisteria");
		Image icon = new Image("icon.png");
		stage.getIcons().add(icon);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
	}
	
	// method associated with the button for monthly goals 
	@FXML
	public void handleGoalsButton(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/monthlytask/monthlygoals.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setTitle("Wisteria");
		Image icon = new Image("icon.png");
		stage.getIcons().add(icon);
    	stage.setResizable(false);
    	stage.setScene(scene);
    	stage.show();	
	}
	
	// method associated with the button for writing a journal entry 
	@FXML
	public void handleMakeJournalEntryButton(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/journal/addentry.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setTitle("Wisteria");
		Image icon = new Image("icon.png");
		stage.getIcons().add(icon);
    	stage.setResizable(false);
    	stage.setScene(scene);
    	stage.show();	
	}
	
	// method associated with the button for the daily task or to-do list 
	@FXML
	public void handleTodoButton(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/dailytask/dailytask.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setTitle("Wisteria");
		Image icon = new Image("icon.png");
		stage.getIcons().add(icon);
    	stage.setResizable(false);
    	stage.setScene(scene);
    	stage.show();	
    }
	
	// method associated with the button for to view all the journal entries 
	@FXML
	public void handleJournalButton(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/journal/journal.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setTitle("Wisteria");
		Image icon = new Image("icon.png");
		stage.getIcons().add(icon);
		stage.setScene(scene);
		stage.show();
	}
	
	// method associated with the button for my account 
	public void handleMyAccountButton(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/myaccount/myaccount.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setTitle("Wisteria");
		Image icon = new Image("icon.png");
		stage.getIcons().add(icon);
		stage.setScene(scene);
		stage.show();			
	}
}

