package com.mmall.concurrency.example.singleton;


import com.mmall.concurrency.annoations.NotRecommend;
import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式
 * 单利实例在第一次使用时，进行创建
 * 不推荐
 * 同一时间只能有一个线程来访问来保证线程的安全，造成了性能上的开销
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {
    private SingletonExample3(){}
    //单利对象
    private static SingletonExample3 instance=null;
    //静态的工厂方法
    public static synchronized SingletonExample3 getInstance(){
        if(instance==null){
            instance=new SingletonExample3();
        }
        return instance;
    }
}
