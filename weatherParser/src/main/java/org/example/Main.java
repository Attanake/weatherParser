package org.example;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.example.Parser.Parse;

public class Main {
    static URL url1;
    static Elements days;
    static Element table;
    static Elements maxTemp;
    static Elements minTemp;
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
        Document page = Parser.getPage(url1);
        Main.StartParse(page);
        Main.QueryExecutor(db);;
    }

    private static void StartParse(Document page) throws IOException {
        days = Parse("div[class=date]", page);
        table = Parse("div[class=values]",page).first();
        maxTemp = Parse("div[class=maxt]", table);
        minTemp = Parse("div[class=mint]", table);
    }

    private static void QueryExecutor(DataBaseConnection db) {
        for (Element day : days) {
            String dayString = day.text();
            String maxTempString = maxTemp.get(days.indexOf(day)).toString();
            int maxTempValue = Integer.parseInt(ResHandler.handler("[+-]?\\d+|[−]\\d+", maxTempString));
            String minTempString = minTemp.get(days.indexOf(day)).toString();
            int minTempValue = Integer.parseInt(ResHandler.handler("[+-]?\\d+|[−]\\d+", minTempString));
            System.out.println(dayString);
            System.out.println(maxTempValue);
            System.out.println(minTempValue);
            java.time.LocalDate currentDate = java.time.LocalDate.now();
            if (days.indexOf(day) == 0) {
                PreparedStatement preparedStatement = db.QueryTemperature();
                try {
                    preparedStatement.setDate(1, java.sql.Date.valueOf(currentDate));
                    preparedStatement.setInt(2, maxTempValue);
                    preparedStatement.setInt(3, minTempValue);
                    int rows = preparedStatement.executeUpdate();
                    System.out.println("added " + rows + "rows");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }else{
                PreparedStatement preparedStatement = db.QueryExpectedTemperature();
                try {
                    preparedStatement.setDate(1, ResHandler.DateHandler(dayString));
                    preparedStatement.setInt(2, maxTempValue);
                    preparedStatement.setInt(3, minTempValue);
                    preparedStatement.setDate(4, java.sql.Date.valueOf(currentDate));
                    int rows = preparedStatement.executeUpdate();
                    System.out.println("added " + rows + "rows");
                }catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
