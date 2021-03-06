package com.mmall.concurrency.example.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample1 {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newCachedThreadPool();
        for (int i=0;i<10;i++){
            final int index=i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("task:"+index);
                   /* try {
                        Thread.sleep(100);
                        System.out.println("task:"+index);
                    }catch (Exception e){
                        System.out.println(e);
                    }*/
                }
            });
        }
        executorService.shutdown();
    }
}
