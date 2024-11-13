package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;

public class Parser {
    private static Document getPage() throws IOException {
        String url = "https://www.gismeteo.by/weather-pinsk-4914/10-days/";
        Document page = Jsoup.parse(new URL(url),2000);
        return page;
    }

    public static Element Parse(String el) throws IOException{
        Document page = Parser.getPage();
        return page.select(el).first();
    }
}