package myaccount;

import java.sql.SQLException;


import base.DB;
import base.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import utility.Utility;

public class MyAccountController extends SceneController {

    @FXML
    private Label dobText;

    @FXML
    private Label emailText;

    @FXML
    private Label nameText;
    
    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField oldPassword;

    MyAccountDB db; 
    
    public void initialize() {
    	try {
    		db = new MyAccountDB();
    		nameText.setText(db.getNameOfUser(DB.getUserID()));
    		emailText.setText(db.getEmailOfUser(DB.getUserID()));
    		dobText.setText(db.getDobOfUser(DB.getUserID()));    		
    	}
    	
    	catch(SQLException e) {
    		e.printStackTrace();
    	}   	
    } 
    
    @FXML
    public void changePassword(ActionEvent event) {
    	String oldPass = oldPassword.getText(); 	
    	boolean correctPassword = MyAccountDB.checkPassword(DB.getUserID(), oldPass);
    	   	
    	if (correctPassword) {
    		String newPass = newPassword.getText(); 
    		MyAccountDB.updatePassword(DB.getUserID(), newPass);
    	}
    	
    	else {
    		Utility.showAlert("Alert", "Password entered is incorrect.");
    	}   	
    	
    }
}
