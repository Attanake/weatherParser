package org.example;

import java.io.IOException;

import static org.example.Parser.Parse;

public class Main {
    static String url1 = "https://www.gismeteo.by/weather-pinsk-4914/10-days/";
    public static void main(String[] args) throws IOException{
        Main.StartParse();
    }

    private static void StartParse() throws IOException{
        String date = Parse("div[class=date]", url1).text();
        String maxTempString = Parse("div[class=maxt]", url1).toString();
        String maxTemp = ResHandler.handler("[+-]?\\d+|[−]\\d+", maxTempString);
        String minTempString = Parse("div[class=mint]", url1).toString();
        String minTemp = ResHandler.handler("[+-]?\\d+|[−]\\d+", minTempString);
        System.out.println("Сегодня, " + date + ", Температура воздуха составит от " + minTemp + " до " + maxTemp + " градудсов" );
    }
}