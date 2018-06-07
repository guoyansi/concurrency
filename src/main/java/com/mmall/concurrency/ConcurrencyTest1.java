package com.mmall.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ConcurrencyTest1 {
    //请求总数
    public  static  int clientTotal=5000;
    //同事并发执行的线程数
    public static int threadTotal=200;
    public static  int count=0;
    public static void main(String[] args) throws  Exception{
        //线程池
        ExecutorService executorService= Executors.newCachedThreadPool();
        //控制同一时间的并发量 threadTotal
        final Semaphore semaphore=new Semaphore(threadTotal);
        final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);
        for (int i=0;i<clientTotal;i++){
            Runnable rn=new Mythread();
            Thread thread=new Thread(rn);
            semaphore.acquire();
            executorService.execute(thread);
            semaphore.release();
           // countDownLatch.countDown();
        }
        countDownLatch.await(2, TimeUnit.SECONDS);
       // countDownLatch.await();
        executorService.shutdown();
        System.out.println("count:"+count);
    }
    static class Mythread implements  Runnable{
        @Override
        public void run() {
            try {
                add();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        private  void add(){
            count++;
        }
    }
}


