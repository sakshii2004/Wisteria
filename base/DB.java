package base;

import java.sql.*;


public class DB {
    private static final String URL = "jdbc:mariadb://localhost:3306/wisteria";
    private static final String USERNAME = "sakshi";
    private static final String PASSWORD = "sakshi123";

    protected static Connection con = null;
    
    private static int userID;

    public static int getUserID() {
        return userID;
    }

    public static void setUserID(int userID) {
        DB.userID = userID;
    }
    
    public DB() throws SQLException {
        con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
