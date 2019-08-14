package com.lize.test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadPoolTest {
    public static void main(String[] args) {
//        test();
//        System.out.println("main");
        new MyThread("低级", 1).start();
        new MyThread("中级", 5).start();
        new MyThread("高级", 10).start();
        System.out.println(Thread.currentThread().getPriority());
    }

    public static void test() {
        //SomeThread thread = new SomeThread();
        //Thread thread = new Thread(new SomeRunnable());
        try{
            FutureTask oneTask = new FutureTask(new SomeCallable());
            Thread thread = new Thread(oneTask);
            thread.start();
            System.out.println(oneTask.get());
            Thread.sleep(3000);
            System.out.println(oneTask.get());
            Thread.sleep(5000);
        }catch (Exception e){
            System.out.println("异常");
        }
    }
}

class MyThread extends Thread{
    public MyThread(String name,int pro){
        super(name);   //设置线程名
        this.setPriority(pro);   //设置优先级
    }

    @Override
    public void run(){
        for(int i =0;i<20;i++){
            System.out.println(this.getName() + "线程第"+ i+ "次执行！");
            if(i % 5 ==0){
                Thread.yield();
            }
        }
    }
}


class SomeCallable implements Callable {
    @Override
    public Object call() throws Exception {
        return "OK";
    }
}
class SomeRunnable implements Runnable{
    @Override
    public void run() {
            for(int i=0;i<10;i++){
                System.out.println("run ="+i);
            }
    }
}
class SomeThread extends Thread {
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println("i ="+i);
        }
    }
}

