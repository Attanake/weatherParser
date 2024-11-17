package org.example;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResHandler {
    public static String handler(String compile, String obj) {
        Pattern pattern = Pattern.compile(compile);
        Matcher mather = pattern.matcher(obj);
        if (mather.find()) {
            return mather.group();
        }
        return "Can't match the temp";
    }

    public static java.sql.Date DateHandler(String day) {
        String currentDate = java.time.LocalDate.now().toString();
        String dayRes = day;
        String month = currentDate.substring(5,7);
        String year = currentDate.substring(0,4);
        try {
            if (Integer.parseInt(dayRes) < Integer.parseInt(currentDate.toString().substring(8, 10))) {
                month =""+(Integer.parseInt(currentDate.toString().substring(5,7))+1);
                if(Integer.parseInt(month) == 13){
                    month = "01";
                    year = ""+(Integer.parseInt(currentDate.toString().substring(0,5))+1);
                }
            }
        }catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        LocalDate date = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        return java.sql.Date.valueOf(date);
    }
}