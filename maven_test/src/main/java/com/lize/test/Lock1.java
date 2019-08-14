package com.lize.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock1 {
    public static void  main(String[] args){
        Bank people = new Bank();
        System.out.println("测试" );

        test();
    }
    public static void test(){
        Bank bank = new Bank();
        LockTest new_thread = new LockTest(bank);
        System.out.println("线程1");
        Thread thread1 = new Thread(new_thread);
        thread1.start();
        System.out.println("线程2");
        Thread thread2 = new Thread(new_thread);
        thread2.start();
    }

}
//可重入锁 ReentrantLock  ,ReentrantLock还可以创建公平锁，但是会大幅度降低运行效率，不建议使用
class Bank2{
    private int account = 100;

    private Lock lock = new ReentrantLock();
    public int getAccount(){
        return account;
    }
    public void save(int money) {
        lock.lock();
        try{
            account += money;
        }finally{
            lock.unlock();
        }

    }
}




///////////////////////////////////////////////////////////////////////////////
class LockTest implements Runnable{

    private Bank bank;
    public LockTest(Bank bank){
        this.bank = bank;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            // bank.save1(10);
            bank.subMoney(10);
            bank.lookMoney();
        }
    }
}
//synchronized 不可重入锁
class Bank{
    private int count = 50;

    public void addMoney(int money){
        synchronized (this){
            try{
                Thread.sleep(3000);
                count += money;
            }catch (InterruptedException e){
                System.out.println("异常");
            }
        }
        System.out.println(System.currentTimeMillis()+"存进："+money);
    }

    public void subMoney(int money){
        synchronized (this){
            if(count-money < 0){
                System.out.println("余额不足");
                return;
            }
            count -= money;
        }
        System.out.println(+System.currentTimeMillis() + "取出："+ money);
    }

    public void lookMoney(){
        System.out.println("账户余额："+count);
    }
}