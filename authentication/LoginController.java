package authentication;

import java.io.IOException;
import java.sql.SQLException;
import base.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import utility.Utility;

public class LoginController extends SceneController {

    @FXML
    private TextField emailLogin;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordLogin;

    @FXML
    private Hyperlink signupLink;
        
    public void loginButton() throws SQLException {
	
        if (emailLogin.getText().isEmpty() || passwordLogin.getText().isEmpty()) { // email and password should not be empty
            Utility.showAlert("Error Message", "Fill both the fields!"); // if they're empty, show alert
        } 
        
        else {            
            UserDB auth = new UserDB();
            boolean authenticated = auth.loginUser(emailLogin.getText(), passwordLogin.getText()); // if user authenticated
            if (authenticated == true) {
            	Utility.showAlert("Information Message", "Successfully Logged In!"); // show successful login alert       	
                try {               	
					// Load the home.fxml file
                	showHome(loginButton.getScene().getWindow());
                } 
                
                catch (IOException e) {
                	Utility.showAlert("Error Message", "Can't load homepage :("); // display alert if home can't be loaded  
                }
            }
            
            else { 
            	Utility.showAlert("Error Message", "Incorrect Username/Password!"); // if authentication fails, means password/username was incorrect
            }
         }
        
     }
}

