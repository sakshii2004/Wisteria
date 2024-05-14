package timer;

import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utility.Utility;

import java.sql.SQLException;

import base.SceneController;

public class TimerController extends SceneController {

    @FXML
    private TextField taskNameField;

    @FXML
    private TextField timeLimitField;

    @FXML
    private Label timerLabel;

    // static variables
    private static Thread timerThread;
    private static int remainingTimeInSeconds; 
    private static boolean isTaskInserted = false;
    
    @FXML
    public void startTimer() throws SQLException {
    	if (!isTaskInserted) {
            String name = taskNameField.getText();
            String timeLimitText = timeLimitField.getText();
            if (name.isEmpty() || timeLimitText.isEmpty()) {
                Utility.showAlert("Error", "Please enter task name and time.");
                return;
            }
            
            try {
                int timeLimit = Integer.parseInt(timeLimitText);
                if (timeLimit <= 0) {
                    Utility.showAlert("Error", "Please enter a positive integer for time.");
                    return;
                }
                
                TaskDB taskDB = new TaskDB();
                TimedTask task = new TimedTask(name, timeLimit);
                taskDB.insertTimedTask(task);
                isTaskInserted = true;
            } catch (NumberFormatException e) {
                Utility.showAlert("Error", "Please enter time in integer (minutes).");
                return;
            } catch (Exception e) {
                Utility.showAlert("Error", "An error occurred while inserting the task.");
                return;
            }
        }

        // only start the timer if it's not already running
        if (timerThread == null || !timerThread.isAlive()) {
            remainingTimeInSeconds = Integer.parseInt(timeLimitField.getText()) * 60;

            timerThread = new Thread(() -> {
                while (remainingTimeInSeconds > 0) {
                    int minutes = remainingTimeInSeconds / 60;
                    int seconds = remainingTimeInSeconds % 60;

                    String formattedTime = String.format("%02d:%02d", minutes, seconds);
                    Platform.runLater(() -> updateTimerLabel(formattedTime));
                    
                    try {
                        Thread.sleep(1000); // Update every second
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    remainingTimeInSeconds--;
                }
                
                // reset isTaskInserted after timer ends
                isTaskInserted = false;

                // display task name and time limit in the alert message
                Platform.runLater(() -> Utility.showAlert("Timer Alert", "Your timer of " + timeLimitField.getText() + " minute(s) for " + taskNameField.getText() + " is up!"));
            });

            timerThread.start();
        }
    }

    public void updateTimerLabel(String time) {
        Platform.runLater(() -> {
            timerLabel.setText(time);
        });
    }

    public void refreshUI() {
    	if (timerThread != null && timerThread.isAlive()) {
            int remainingTime = remainingTimeInSeconds;

            // update timer label
            Platform.runLater(() -> {
                timerLabel.setText(formatTime(remainingTime));
            });
            
            // schedule the refresh to happen again after 1 second
            Platform.runLater(() -> {
                refreshUI();
            });
        }
        else {
            // if timer has finished, set the timer label to "00:00"
            Platform.runLater(() -> {
                timerLabel.setText("00:00");
            });
        }    
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }
}