package com.dwl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

public class Consumer {
    public static void main(String[] args){
        //test1();
         test2();
    }

    //DemoService的测试用例
    private static void test2(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        context.start();

        DemoService demoService = (DemoService) context.getBean("demoService"); //
        String hello = demoService.sayHello("tom"); // ִ
        System.out.println(hello); //
        //String info = demoService.showInfo("测是不是由序列化导致的错误");
        //System.out.println(info); //

        List list = demoService.getUsers();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                try{
                    System.out.println(demoService.toPlay((byte[]) list.get(i)));
                }catch (Exception e){

                }
            }
        }
         //System.out.println(demoService.hehe());
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //DubboService的测试用例
    private static void test1(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        System.out.println("consumer start");
        DubboService service = context.getBean(DubboService.class);
        System.out.println("consumer started");
        System.out.println(service.getMessage("dubbo"));
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
