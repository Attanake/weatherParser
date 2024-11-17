package org.example;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
        //DataBaseConnection db = new DataBaseConnection();
        //db.DBConnect("WeatherDB","postgres");
        Document page = Parser.getPage(url1);
        Main.StartParse(page);
    }

    private static void StartParse(Document page) throws IOException {
        Elements dates = Parse("div[class=date]", page);
        Element table = Parse("div[class=values]",page).first();
        Elements maxTempString = Parse("div[class=maxt]", table);
        //String maxTemp = ResHandler.handler("[+-]?\\d+|[−]\\d+", maxTempString);
        Elements minTempString = Parse("div[class=mint]", table);
        //String minTemp = ResHandler.handler("[+-]?\\d+|[−]\\d+", minTempString);
        for(Element date : dates){
            System.out.println(date.text());
            System.out.println(ResHandler.handler("[+-]?\\d+|[−]\\d+", maxTempString.get(dates.indexOf(date)).toString()));
            System.out.println(ResHandler.handler("[+-]?\\d+|[−]\\d+", minTempString.get(dates.indexOf(date)).toString()));
        }

    }
}
