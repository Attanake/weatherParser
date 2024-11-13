package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResHandler {
    public static String handler(String compile, String obj){
        Pattern pattern = Pattern.compile(compile);
        Matcher mather = pattern.matcher(obj);
        if (mather.find()){
            return mather.group(1);
        }
        return "Can't match the temp";
    }
}
