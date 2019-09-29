package com.lize.pachong.parse;

import com.lize.pachong.model.JdongBook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class JdParse {

    public static List<JdongBook> getData(String entity) {
        List<JdongBook> data = new ArrayList<>();
        //采用jsoup解析
        Document doc = Jsoup.parse(entity);

        //根据页面内容分析出需要的元素
        Elements elements = doc.select("ul[class=gl-warp clearfix]").select("li[class=gl-item]");
        for (Element element: elements) {
            JdongBook book = new JdongBook();
            book.setBookId(element.attr("data-sku"));
            book.setBookName(element.select("div[class=p-name p-name-type-2]").select("em").text());
            book.setBookPrice(element.select("div[class=p-price]").select("strong").select("i").text());

            data.add(book);
        }
        return data;
    }
}
