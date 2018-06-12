package com.mmall.concurrency.example.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Slf4j
public class GuavaCacheExample2 {

    public static void main(String[] args) {

        Cache<String, Integer> cache = CacheBuilder.newBuilder()
                .maximumSize(10) // 最多存放10个数据
                .expireAfterWrite(10, TimeUnit.SECONDS) // 缓存10秒
                .recordStats() // 开启记录状态数据功能
                .build();

        System.out.println( cache.getIfPresent("key1")); // null
        cache.put("key1", 1);
        System.out.println( cache.getIfPresent("key1")); // 1
        cache.invalidate("key1");
        System.out.println( cache.getIfPresent("key1")); // null

        try {
            System.out.println( cache.get("key2", new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return -1;
                }
            })); // -1
            cache.put("key2", 2);
            System.out.println( cache.get("key2", new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return -1;
                }
            })); // 2

            System.out.println( cache.size()); // 1

            for (int i = 3; i < 13; i++) {
                cache.put("key" + i, i);
            }
            System.out.println( cache.size()); // 10

            System.out.println( cache.getIfPresent("key2")); // null

            Thread.sleep(11000);

            System.out.println( cache.get("key5", new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return -1;
                }
            })); // -1

            System.out.println(cache.stats().hitCount()+";"+cache.stats().missCount());

            System.out.println(cache.stats().hitRate()+";"+cache.stats().missRate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
