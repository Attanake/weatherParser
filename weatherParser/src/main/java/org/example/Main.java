package org.example;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
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
        DataBase db = new DataBase();
        db.DBConnect("WeatherDB","postgres");
        Document page = Parser.getPage(url1);
        Main.StartParse(page);
        System.out.println("Do you want to add today's data to the DataBase? 0/1");
        Scanner scanner = new Scanner(System.in);
        int answ = scanner.nextInt();
        if(answ == 1){
            Main.QueryExecutor(db);
        }
        ChartGenerator.ShowTheChart(db,java.time.LocalDate.of(2024,11,16));
        ChartGenerator.Main();
    }

    private static void StartParse(Document page){
        try {
            days = Parse("div[class=date]", page);
            table = Parse("div[class=values]", page).first();
            maxTemp = Parse("div[class=maxt]", table);
            minTemp = Parse("div[class=mint]", table);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void QueryExecutor(DataBase db) {
        for (Element day : days) {
            String dayString = day.text();
            String maxTempString = maxTemp.get(days.indexOf(day)).toString();
            int maxTempValue = Integer.parseInt(ResHandler.handler("[+-]?\\d+|[−]\\d+", maxTempString));
            String minTempString = minTemp.get(days.indexOf(day)).toString();
            int minTempValue = Integer.parseInt(ResHandler.handler("[+-]?\\d+|[−]\\d+", minTempString));
            java.time.LocalDate currentDate = java.time.LocalDate.now();
            if (days.indexOf(day) == 0) {
                try {
                    PreparedStatement preparedStatement = db.QueryTemperature();
                    preparedStatement.setDate(1, java.sql.Date.valueOf(currentDate));
                    preparedStatement.setInt(2, maxTempValue);
                    preparedStatement.setInt(3, minTempValue);
                    int rows = preparedStatement.executeUpdate();
                    System.out.println("added " + rows + " rows: " + currentDate + " " + maxTempValue + " " + minTempValue);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }else{
                try {
                    PreparedStatement preparedStatement = db.QueryExpectedTemperature();
                    preparedStatement.setDate(1, ResHandler.DateHandler(dayString));
                    preparedStatement.setInt(2, maxTempValue);
                    preparedStatement.setInt(3, minTempValue);
                    preparedStatement.setDate(4, java.sql.Date.valueOf(currentDate));
                    int rows = preparedStatement.executeUpdate();
                    System.out.println("added " + rows + " rows: " + currentDate + " " + maxTempValue + " " + minTempValue);
                }catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
