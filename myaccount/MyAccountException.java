package myaccount;

public class MyAccountException extends Exception {
    
    public MyAccountException() {
        super();
    }
    
    public MyAccountException(String message) {
        super(message);
    }
    
    public MyAccountException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public MyAccountException(Throwable cause) {
        super(cause);
    }
}
