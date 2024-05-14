package authentication;

import java.sql.Date;
import java.sql.SQLException;

public interface Authentication {
		
	public void userSession(String email, String password);
	
	public boolean loginUser(String email, String password);
	
	public boolean registerUser(String name, String email, String password, Date DOB) throws SQLException;
}
