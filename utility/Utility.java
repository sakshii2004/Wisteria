package utility;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;

public class Utility {
	
	public static ArrayList<String> monthNames = new ArrayList<>(Arrays.asList(
            "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE",
            "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"
        ));	
	
	public static void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
	
	public static void showHappyBdayAlert(String name) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Birthday Wishes");
        alert.setHeaderText("Happy Birthday " + name + "!");
        alert.setContentText("Enjoy your day ahead!");
        alert.showAndWait();
    }
	
	public static ArrayList<Integer> getYears() {
		LocalDate date = LocalDate.now();
		int year = date.getYear();
		ArrayList<Integer> years = new ArrayList<>();
		for(int i = 0; i<3; i++) {
			years.add(year+i);
		}
		return years;
	}
	
	public static Date getDate(String monthYear) {
       	Pattern pattern = Pattern.compile("(\\p{Alpha}+) (\\d{4})");
        Matcher matcher = pattern.matcher(monthYear);
        
        // checking if the pattern matches the input
        if (matcher.matches()) {
            // extracting the month and year separately
            String month = matcher.group(1);
            int year = Integer.parseInt(matcher.group(2));
            Month monthEnum = Month.valueOf(month);
    		int monthNumber = monthEnum.getValue();
    		LocalDate localDate = LocalDate.of(year, monthNumber, 1); // day is always 01, we care only about the month and year
        	Date monthAndYear = Date.valueOf(localDate);   
        	return monthAndYear;
        } 
        
        Date currentDate = Date.valueOf(LocalDate.now());
        
        return currentDate;
        
    }
	
	public static int getMonth(Date date) {
		LocalDate monthYear = date.toLocalDate();
		Month month = monthYear.getMonth();
		int monthNumber = month.getValue();	
		return monthNumber;
	}
	
	public static int getYear(Date date) {
		LocalDate monthYear = date.toLocalDate();
		int year = monthYear.getYear();
		return year;
	}

}

