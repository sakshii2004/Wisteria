package calendar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utility.Utility;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import base.SceneController;
import dailytask.DailyTaskController;

public class CalendarController extends SceneController{
	
    ZonedDateTime dateFocus;
    ZonedDateTime today;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar; // flow pane for represents the container for displaying the calendar

    
    public void initialize() {
        dateFocus = ZonedDateTime.now(); //records current date to highlight today's date
        today = ZonedDateTime.now(); 
        drawCalendar(); //makes the calendar
    }

    @FXML
    void backOneMonth(ActionEvent event) { //method to show the calendar of previous month
        dateFocus = dateFocus.minusMonths(1); 
        calendar.getChildren().clear(); //clearing the container 
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) { //method to show the calendar of next month
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear(); //clearing the container 
        drawCalendar();
    }

    private void drawCalendar() {
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        int monthMaxDate = dateFocus.getMonth().maxLength();
        
        // check for leap year
        if (dateFocus.getYear() % 4 != 0 && monthMaxDate == 29) {
            monthMaxDate = 28;
        }
        
        // dateOffset stores the numerical value representing the day of the week for the first day of the month stored in dateFocus
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane(); //creating container
                Rectangle rectangle = new Rectangle(); //creating shape for individual dates in the month
                rectangle.setFill(Color.TRANSPARENT); 
                rectangle.setStroke(Color.BLACK); 
                rectangle.setStrokeWidth(strokeWidth); 
                double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight / 6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                
                stackPane.getChildren().add(rectangle); //adding the rectangle in the stack pane

                int calculatedDate = (j + 1) + (7 * i);
                if (calculatedDate > dateOffset) {
                    int currentDate = calculatedDate - dateOffset;
                    if (currentDate <= monthMaxDate) {
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = -(rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        // add "+" button only for dates in the current month
                        Button addButton = new Button("+");
                        final int selectedDay = currentDate; // Capture the selected day
                        addButton.setOnAction(event -> {
                            // handling the button click 
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/dailytask/dailytask.fxml"));
                                Parent root = loader.load();                                
                                DailyTaskController controller = loader.getController();                                
                                // Convert ZonedDateTime to LocalDate and then to java.sql.Date before passing it
                                LocalDate selectedDate = dateFocus.toLocalDate().withDayOfMonth(selectedDay);                                
                                Date sqlDate = Date.valueOf(selectedDate);
                                
                                // Pass the selected date to the controller
                                try {
									controller.setSelectedDate(sqlDate);
								} 
                                
                                catch (SQLException e) {
                                	Utility.showAlert("Error", "Something went wrong.");
								}                                
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root));
                                stage.show();
                            } 
                            
                            catch (IOException e) {
                            	Utility.showAlert("Error", "Something went wrong.");
                            }
                        });
                        
                        addButton.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, null))); // Set button background color to lavender
                        stackPane.getChildren().add(addButton);
                    }
                    
                    if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
                        rectangle.setStroke(Color.GREEN);
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }
}

