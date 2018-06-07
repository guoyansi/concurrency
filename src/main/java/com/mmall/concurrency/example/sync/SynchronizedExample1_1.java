package com.mmall.concurrency.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 当前实例说明：
 * 作用当前对象，对于不同对象无法约束,会出现交叉执行
 *
 * test1 和 test2是等同的锁
 */
public class SynchronizedExample1_1 {

    //修饰一个代码块
    public void test1(int t){
        synchronized (this){
            for (int i=0;i<10;i++){
                System.out.println("test1_"+t+":"+i);
            }
        }
    }
    //修饰一个方法
    public synchronized void  test2(int t){
        for (int i=0;i<10;i++){
            System.out.println("test2_"+t+":"+i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1_1 example1=new SynchronizedExample1_1();
        SynchronizedExample1_1 example2=new SynchronizedExample1_1();
        ExecutorService executorService= Executors.newCachedThreadPool();
        executorService.execute(()->{
            example1.test1(1);
        });
        executorService.execute(()->{
            example2.test1(2);
        });
    }
}
