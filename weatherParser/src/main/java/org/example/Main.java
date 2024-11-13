package org.example;

import java.io.IOException;

import static org.example.Parser.Parse;

public class Main {
    public static void main(String[] args) throws IOException {
        String date = Parse("div[class=date]").text();
        String maxTempString = Parse("div[class=maxt]").toString();
        String maxTemp = ResHandler.handler("value=\"(\\d+)\"", maxTempString);
        String minTempString = Parse("div[class=mint]").toString();
        String minTemp = ResHandler.handler("value=\"(\\d+)\"", minTempString);
        System.out.println("Сегодня, " + date + ", Температура воздуха составит от " + minTemp + " до " + maxTemp + " градудсов" );
    }
}