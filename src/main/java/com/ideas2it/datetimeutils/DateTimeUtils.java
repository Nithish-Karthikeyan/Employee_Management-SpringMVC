package datetimeutils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;


/**
 * This class is for Date Time Calculation 
 * Get current time
 *
 * @author Nithish K
 * @version 1.0
 * @since 19.09.2022
 */ 
public class DateTimeUtils {
    /**
     * Get the current date and time
     * @return current_date 
     */
    public String getDate() {
        String current_date;
        String dateFormat= "yyyy-MM-dd HH:mm:ss";
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
        current_date = date.format(dateFormatter);
        return current_date;
    }

    /**
     * Validates the Start date and end date of the leave given by the employee
     * @param fromDate Start date of the leave
     * @param toDate End date of the leave
     * @return boolean
     */
    public boolean validateLeaveDate(String fromDate, String toDate) {
        boolean isValidDate = false;
        LocalDate startDate = LocalDate.parse(fromDate);
        LocalDate endDate = LocalDate.parse(toDate);
        int difference = startDate.compareTo(endDate);

        if (difference == 0) {
            isValidDate = true;
        } else if (difference < 0) {
            isValidDate = true;
        } else {
            isValidDate = false;
        }
        return isValidDate;
    }

    /**
     * Parse the String to local Date format
     * @param date gets the string type date from controller
     * @return LocalDate
     */
    public LocalDate getLocalDateFormat(String date) {
        return LocalDate.parse(date);
    }

    /**
     * Gets the difference between two dates
     *
     * @param fromDate from Date of the leave Record
     * @param toDate from Date of the leave Record
     * @return int - returns the leave count
     */
    public int findLeaveCount(LocalDate  fromDate, LocalDate toDate) {
        int leaveCount = 0;
        Period difference = Period.between(fromDate, toDate);
        leaveCount = difference.getDays();
        return leaveCount;
    }
}