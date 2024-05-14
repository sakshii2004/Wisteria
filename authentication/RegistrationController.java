package authentication;

import java.sql.Date;
import java.time.LocalDate;
import base.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utility.Utility;

public class RegistrationController extends SceneController {

    @FXML
    private DatePicker dobPicker;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Hyperlink signinLink;

    @FXML
    void registerButtonAuth() {
    	String name = nameField.getText(); // get name
        String email = emailField.getText(); // get email
        String password = passwordField.getText(); // get password
        LocalDate dob = dobPicker.getValue(); // get date of birth
        
        try {
        	UserDB registerationAuth = new UserDB();
            
            if (registerationAuth.emailExists(email)) { // if email exists in database
                Utility.showAlert("Email Exists", "This email is already registered. Please login or use a different email.");
                return; //show alert and exit the code block
            }
            
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || dob == null) {
            	Utility.showAlert("Invalid Input", "Please fill in all fields and provide valid information.");
                return; //if any field is empty, show alert and exit the code block
            }
            
            else {            	
            	registerationAuth.registerUser(name, email, password, Date.valueOf(dob)); // adding new user information to database
            	Utility.showAlert("Registration Successful", "You have been successfully registered!");
            }            
        } 
      
        catch(Exception e) {
        	Utility.showAlert("Registration Error", "Failed to register. Please try again later."); //if registration fails, show alert
 
        }  
    }
}

