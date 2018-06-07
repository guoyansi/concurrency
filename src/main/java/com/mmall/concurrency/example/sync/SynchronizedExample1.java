package com.mmall.concurrency.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 当前实例说明：
 * 作用当前对象，对于不同对象无法约束
 */
public class SynchronizedExample1 {

    //修饰一个代码块
    public void test1(){
        synchronized (this){
            for (int i=0;i<10;i++){
                System.out.println("test1:"+i);
            }
        }
    }
    //修饰一个方法
    public synchronized void  test2(){
        for (int i=0;i<10;i++){
            System.out.println("test2:"+i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1=new SynchronizedExample1();
        ExecutorService executorService= Executors.newCachedThreadPool();
        executorService.execute(()->{
            //example1.test1();
            example1.test2();
        });
        executorService.execute(()->{
            //example1.test1();
            example1.test2();
        });
    }
}
