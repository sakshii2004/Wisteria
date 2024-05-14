module WisteriaApplication {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;
	opens authentication to javafx.graphics, javafx.fxml, javafx.base;
	opens base to javafx.graphics, javafx.fxml, javafx.base;
	opens home to javafx.graphics, javafx.fxml, javafx.base;
	opens tasks to javafx.graphics, javafx.fxml, javafx.base;
	opens utility to javafx.graphics, javafx.fxml, javafx.base;
	opens timer to javafx.graphics, javafx.fxml, javafx.base;
	opens calendar to javafx.graphics, javafx.fxml, javafx.base;
	opens dailytask to javafx.graphics, javafx.fxml, javafx.base;
	opens monthlytask to javafx.graphics, javafx.fxml, javafx.base;	
	opens journal to javafx.graphics, javafx.fxml, javafx.base;	
	opens myaccount to javafx.graphics, javafx.fxml, javafx.base;	
}
