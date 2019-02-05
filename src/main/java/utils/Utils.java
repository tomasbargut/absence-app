package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletRequest;

/**
 * Utils
 */

public class Utils {
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static String getCurrentTime() {
        Date date = new Date();

        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        String formattedDate = dateFormat.format(date);

        return formattedDate;

    }

    public static String getStringValue(ServletRequest request, String paramName, String defaultValue) {
        if (request.getParameter(paramName) != null) {
            return String.valueOf(request.getParameter(paramName));
        } else {
            return defaultValue;
        }
    }

    public static Integer getIntValue(ServletRequest request, String paramName, Integer defaultValue) {
        if (request.getParameter(paramName) != null) {
            return Integer.valueOf(request.getParameter(paramName));
        } else {
            return defaultValue;
        }
    }

}
