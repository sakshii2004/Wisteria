package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import utility.Utility;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/authentication/login.fxml"));			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/utility/application.css").toExternalForm());
			primaryStage.setTitle("Wisteria");
			Image icon = new Image("icon.png");
			primaryStage.getIcons().add(icon);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		
		catch(Exception e) {
			Utility.showAlert("Error!", "Can't load Wisteria :(");
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
