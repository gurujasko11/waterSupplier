package Utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by busz on 10.02.17.
 */
public class DecimalUtil {
    public static final String PATTERN = "#0.00";
    public static final DecimalFormat decimalFormat = new DecimalFormat(PATTERN, DecimalFormatSymbols.getInstance(Locale.US));

    public static String format(Number n){
        return n == null ? null : decimalFormat.format(n);
    }

    public static Number parse(String n){
        try {
            return decimalFormat.parse(n);
        } catch (ParseException e) {
            return null;
        }
    }

    public static boolean validDecimal(String n){
        return parse(n) != null;
    }
}
