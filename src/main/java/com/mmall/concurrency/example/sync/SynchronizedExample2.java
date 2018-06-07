package com.mmall.concurrency.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedExample2 {

    //修饰一个类
    public static void test1(int t){
        synchronized (SynchronizedExample2.class){
            for (int i=0;i<10;i++){
                System.out.println("test1:_"+t+"_"+i);
            }
        }
    }
    //修饰一个静态方法
    public static synchronized void  test2(int t){
        for (int i=0;i<10;i++){
            System.out.println("test2:_"+t+"_"+i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 example1=new SynchronizedExample2();
        ExecutorService executorService= Executors.newCachedThreadPool();
        executorService.execute(()->{
            //example1.test1(1);
            example1.test2(1);
        });
        executorService.execute(()->{
            //example1.test1(2);
            example1.test2(2);
        });
    }
}
