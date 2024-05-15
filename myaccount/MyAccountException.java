package myaccount;
import utility.Utility;

public class MyAccountException extends Exception {
    
    public MyAccountException() {
        super();
        Utility.showAlert("Error","An error has occurred");
    }
    
    public MyAccountException(String message) {
        super(message);
        Utility.showAlert("Error", message);
    }
    
    public MyAccountException(String message, Throwable cause) {
        super(message, cause);
        Utility.showAlert("Error", message);
    }
    
    public MyAccountException(Throwable cause) {
        super(cause);
        Utility.showAlert("Error","An error has occurred");
    }
}
