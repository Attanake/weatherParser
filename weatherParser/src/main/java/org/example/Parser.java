package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;

public class Parser {
    private static Document getPage(String urlPar) throws IOException {
        return Jsoup.parse(new URL(urlPar),20000);
    }

    public static Element Parse(String el, String urlPar) throws IOException{
        Document page = Parser.getPage(urlPar);
        return page.select(el).first();
    }
}