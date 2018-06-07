package com.mmall.concurrency.example.syncContainer;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 同步容器出现线程不安全的现象
 * @author guoyansi
 *下面例子是边remove边get,造成索引越界
 */
@ThreadSafe
public class VectorExample2 {
	
	private static Vector<Integer> vector=new Vector<Integer>();
	
	public static void main(String[] args) throws Exception{
		for(int i=0;i<10;i++) {
			vector.add(i);
		}

		while(true) {
			Thread thread1=new Thread() {
				@Override
				public void run() {
					for(int i=0;i<10;i++) {
						vector.remove(i);
					}
				}
			};
			Thread thread2=new Thread() {
				@Override
				public void run() {
					for(int i=0;i<10;i++) {
						vector.get(i);
					}
				}
			};
			thread1.start();
			thread2.start();
		}
	}
}
