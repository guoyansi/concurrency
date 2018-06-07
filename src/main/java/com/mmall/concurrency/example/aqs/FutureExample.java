package com.mmall.concurrency.example.aqs;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {
    static  class MyCallable implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println("do something in callable");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args)  throws  Exception{
        ExecutorService executorService= Executors.newCachedThreadPool();
        Future<String> future=executorService.submit(new MyCallable());
        System.out.println("do something in main");
        Thread.sleep(1000);
        String result=future.get();
        System.out.println("result:"+result);
    }
}
