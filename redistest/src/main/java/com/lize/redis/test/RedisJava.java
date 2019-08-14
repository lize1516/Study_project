package com.lize.redis.test;

import redis.clients.jedis.Jedis;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RedisJava {
    public static void main(String[] args) {

        //test1();
        test2();
        test3();
    }

    public static void test4(){
        Jedis jedis = connectionJedis();

        //存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list1", "Google");
        jedis.lpush("site-list2", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0, 2);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("列表项为: " + list.get(i));
        }
    }

    public static void test3(){
        Jedis jedis = connectionJedis();

        //获取数据并输出
        Set<String> keys = jedis.keys("*");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()){
            String key = it.next();
            System.out.println(key);
        }
    }

    public static void test2() {
        Jedis jedis = connectionJedis();
        //存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0, 2);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("列表项为: " + list.get(i));
        }
    }

    public static void test1() {
        Jedis jedis = connectionJedis();
        jedis.set("runoobkey", "www.runoob.com");
        //获取存储数据并输出
        System.out.println("redis 存储的字符串：" + jedis.get("runoobkey"));
    }

    private static Jedis connectionJedis() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //查看服务是否正常运行
        System.out.println("服务正在运行:" + jedis.ping());
        return jedis;
    }
}
