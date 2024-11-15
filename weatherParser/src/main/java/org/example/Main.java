package org.example;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.example.Parser.Parse;

public class Main {
    static URL url1;

    static {
        try {
            url1 = new URL("https://www.gismeteo.by/weather-pinsk-4914/10-days/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException{
        DataBaseConnection db = new DataBaseConnection();
        db.DBConnect("WeatherDB","postgres");
        Main.StartParse(url1);
    }

    private static void StartParse(URL url) throws IOException {
        String date = Parse("div[class=date]", url).text();
        String maxTempString = Parse("div[class=maxt]", url).toString();
        String maxTemp = ResHandler.handler("[+-]?\\d+|[−]\\d+", maxTempString);
        String minTempString = Parse("div[class=mint]", url).toString();
        String minTemp = ResHandler.handler("[+-]?\\d+|[−]\\d+", minTempString);
        System.out.println("Сегодня, " + date + ", Температура воздуха составит от " + minTemp + " до " + maxTemp + " градудсов");
    }
}
