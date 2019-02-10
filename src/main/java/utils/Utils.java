package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utils
 */


public class Utils {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static String getCurrentTime() {
        Date date= new Date();
    
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    
        String formattedDate=dateFormat.format(date);
    
       return formattedDate;
    
    }
    public static boolean isNullOrEmpty(String str) {
		return (str == null || str.isEmpty());
	}
}