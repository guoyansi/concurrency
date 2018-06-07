package com.mmall.concurrency.example.syncContainer;

import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 同步容器出现线程不安全的现象
 * 
 * @author guoyansi 下面例子是边remove边get,造成索引越界
 */
@ThreadSafe
public class VectorExample3 {

	
	/**
	 * 异常
	 * java.util.ConcurrentModificationException
	 * @param vector
	 */
	private static void test1(Vector<Integer> vector) {
		for (Integer v : vector) {
			if (v.equals(3)) {
				vector.remove(v);
			}

		}
	}
/**
 * 异常
 * java.util.ConcurrentModificationException
 * @param vector
 */
	private static void test2(Vector<Integer> vector) {
		Iterator<Integer> iterator = vector.iterator();
		while (iterator.hasNext()) {
			Integer i = iterator.next();
			if (i.equals(3)) {
				vector.remove(i);
			}

		}
	}
/**
 * 没有异常
 * @param vector
 */
	private static void test3(Vector<Integer> vector) {
		for (int i = 0; i < vector.size(); i++) {
			if (vector.get(i).equals(3)) {
				vector.remove(i);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Vector<Integer> vector = new Vector<Integer>();
		vector.add(1);
		vector.add(2);
		vector.add(3);
test3(vector);
	}
}
