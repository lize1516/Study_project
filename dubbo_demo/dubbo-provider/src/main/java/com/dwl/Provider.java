package com.dwl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Provider {
    public static void main(String[] args){
        //test1();
        test2();
    }

    //DemoServiceImpl的测试接口
    private static void test2(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"provider.xml"});
        context.start();
        System.out.println("provider started");
        try{
            System.in.read();    // 为保证服务一直开着，利用输入流的阻塞来模拟
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //DubboServiceImpl的测试接口
    private static void test1(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:provider.xml");
        System.out.println(context.getDisplayName() + ": 什么鬼 here");
        context.start();
        System.out.println("provider started");
        try{
            System.in.read();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
