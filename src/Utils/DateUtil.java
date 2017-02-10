package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DateUtil {

    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);


    public static String format(LocalDate date) {
        return date == null ? null : dateFormatter.format(date);
    }

    public static LocalDate parse(String dateString) {
        try {
            return dateFormatter.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static boolean validDate(String dateString) {
        return DateUtil.parse(dateString) != null;
    }
}