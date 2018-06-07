package com.mmall.concurrency.example.threadLocal;

public class RequestHolder {
	private final static ThreadLocal<Long> requestHolder = new ThreadLocal<Long>();

	public static void add(Long id) {
		requestHolder.set(id);
	}

	public static Long getId() {
		return requestHolder.get();
	}

	public static void remive() {
		requestHolder.remove();
	}
}
