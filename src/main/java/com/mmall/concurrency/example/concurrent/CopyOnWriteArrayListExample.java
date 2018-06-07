package com.mmall.concurrency.example.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.mmall.concurrency.annoations.NoThreadSafe;
import com.mmall.concurrency.annoations.ThreadSafe;


@ThreadSafe
public class CopyOnWriteArrayListExample {

	//请求总和
	public static int clientTotal=5000;
	//同时并发执行线程数
	public static int threadTotal=200;
	
	private static List<Integer> list=new CopyOnWriteArrayList<Integer>();
	
	public static void main(String[] args) throws Exception{
		ExecutorService executorService=Executors.newCachedThreadPool();
		final Semaphore semaphore=new Semaphore(threadTotal);
		final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);
		for(int i=0;i<clientTotal;i++) {
			final int count=i;
			executorService.execute(()->{
				try {
					semaphore.acquire();
					update(count);
					semaphore.release();
				} catch (Exception e) {
					e.printStackTrace();
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
		System.out.println("size:"+list.size());
	}
	private static void update(int i) {
		list.add(i);
	}
}
