package com.lize.pachong.service;

import com.lize.pachong.model.JdongBook;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * 程序入口，在此声明客户端，并向服务器发送请求
 */
public class JdongMain {
    public static void main(String[] args){

        HttpClient client = HttpClients.createDefault();
        String url = "http://search.jd.com/Search?keyword=Python&enc=utf-8&book=y&wq=Python&pvid=33xo9lni.p4a1qb";
        List<JdongBook> bookList = null;
        try{
            bookList = URLHandle.urlParser(client, url);
        }catch (ParseException | IOException e){
            e.printStackTrace();
        }
        for(JdongBook book : bookList){
            System.out.println(book);
        }
    }

}
