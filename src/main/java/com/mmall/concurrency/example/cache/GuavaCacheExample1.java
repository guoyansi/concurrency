package com.mmall.concurrency.example.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class GuavaCacheExample1 {
	public static void main(String[] args) {
		LoadingCache<String, Integer> cache=CacheBuilder.newBuilder()
				.maximumSize(10)
				.expireAfterWrite(10, TimeUnit.SECONDS)
				.recordStats()
				.build(new CacheLoader<String, Integer>(){
					@Override
					public Integer load(String key) throws Exception {
						return -1;
					}
				});
		System.out.println(cache.getIfPresent("key1"));
		cache.put("key1", 1);
		System.out.println(cache.getIfPresent("key1"));
		cache.invalidate("key1");
		System.out.println(cache.getIfPresent("key1"));
		 try {
	            System.out.println(cache.get("key2")); // -1
	            cache.put("key2", 2);
	            System.out.println(cache.get("key2")); // 2

	            System.out.println(cache.size()); // 1

	            for (int i = 3; i < 13; i++) {
	                cache.put("key" + i, i);
	            }
	            System.out.println(cache.size()); // 10

	            System.out.println(cache.getIfPresent("key2")); // null

	            Thread.sleep(11000);

	            System.out.println(cache.get("key5")); // -1

	            System.out.println(cache.stats().hitCount()+";"+cache.stats().missCount());

	            System.out.println(cache.stats().hitRate()+";"+cache.stats().missRate());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}
