package com.lize.pachong.service;

import com.lize.pachong.model.JdongBook;
import com.lize.pachong.parse.JdParse;
import com.lize.pachong.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过URL和客户端（client）处理请求返回的数据
 */
public class URLHandle {

    public static List<JdongBook> urlParser(HttpClient client, String url) throws ParseException, IOException {
        List<JdongBook> data = new ArrayList<>();

        //获取响应资源
        HttpResponse response = HttpUtils.getHtml(client, url);
        //获取响应状态码
        int statusCode = response.getStatusLine().getStatusCode();
        if(statusCode == 200){//内容获取成功
            //获取响应实体内容，并且将其转换为utf-8形式的字符串编码
            String entity = EntityUtils.toString(response.getEntity(), "utf-8");
            data = JdParse.getData(entity);
        }else {
            //释放资源实体
            EntityUtils.consume(response.getEntity());
        }
        return data;
    }
}
