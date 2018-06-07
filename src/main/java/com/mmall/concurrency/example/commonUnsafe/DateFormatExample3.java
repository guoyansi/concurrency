package com.mmall.concurrency.example.commonUnsafe;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.mmall.concurrency.annoations.ThreadSafe;


/**
 * SimpleDateFormat 是不允许多线程操作，否则出现异常,使用堆栈封闭（局部变量）可以不报异常
 * @author guoyansi
 *
 */
@ThreadSafe
public class DateFormatExample3 {

	//请求总和
	public static int clientTotal=10;
	//同时并发执行线程数
	public static int threadTotal=2;
	
	private static DateTimeFormatter dateTimeFormatter=DateTimeFormat.forPattern("yyyyMMdd");
	
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
	}
	private static void update(int count) {
		Date date=DateTime.parse("20180208",dateTimeFormatter).toDate();
		System.out.println(count+" "+date);
	}
}
