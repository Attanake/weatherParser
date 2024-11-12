package org.example;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Document page = Parser.getPage();
        Element weatherClass = page.select("div[data-widget=weather-parameters]").first();
        String date = "";
        System.out.println("Сегодня, " + date + ". Температура воздуха составит " );
    }
}