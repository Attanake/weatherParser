package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class Parser {
    public static Document getPage(URL urlPar) throws IOException {
        return Jsoup.parse(urlPar,2000);
    }

    public static Elements Parse(String el, Document page){
        Elements item = page.select("div[class=widget-items js-scroll-item]");
        return item.select(el);
    }

    public static Elements Parse(String el, Element table){
        return table.select(el);
    }
}