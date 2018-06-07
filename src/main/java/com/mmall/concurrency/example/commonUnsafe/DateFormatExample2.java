package com.mmall.concurrency.example.commonUnsafe;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.mmall.concurrency.annoations.NoThreadSafe;

/**
 * SimpleDateFormat 是不允许多线程操作，否则出现异常,使用堆栈封闭（局部变量）可以不报异常
 * @author guoyansi
 *
 */
@NoThreadSafe
public class DateFormatExample2 {

	//请求总和
	public static int clientTotal=5000;
	//同时并发执行线程数
	public static int threadTotal=200;
	
	public static void main(String[] args) throws Exception{
		ExecutorService executorService=Executors.newCachedThreadPool();
		final Semaphore semaphore=new Semaphore(threadTotal);
		final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);
		for(int i=0;i<clientTotal;i++) {
			executorService.execute(()->{
				try {
					semaphore.acquire();
					update();
					semaphore.release();
				} catch (Exception e) {
					e.printStackTrace();
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
	}
	private static void update() {
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
		try {
			simpleDateFormat.parse("20180208");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
