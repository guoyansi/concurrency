package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子性修改某个实例的成员值，
 * 这个成员必须是volatile，并且不是static修饰的值
 * 该例子修改AtomicExample5实例对象的count成员值
 */
@Slf4j
@ThreadSafe
public class AtomicExample5 {
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater=AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");
    public volatile int count=100;

    public static void main(String[] args)  throws  Exception{
        AtomicExample5 example5=new AtomicExample5();
        if(updater.compareAndSet(example5,100,120)){
            System.out.println("count1:"+example5.count);
        }
        if (updater.compareAndSet(example5, 120, 130)) {
            System.out.println("update success 2:"+example5.count);
        } else {
            System.out.println("update failed"+example5.count);
        }
    }
}
