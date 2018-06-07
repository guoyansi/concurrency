package com.mmall.concurrency.example.singleton;


import com.mmall.concurrency.annoations.NoThreadSafe;

/**
 * 懒汉模式
 * 单利实例在第一次使用时，进行创建
 */
@NoThreadSafe
public class SingletonExample1 {
    private SingletonExample1(){}
    //单利对象
    private static SingletonExample1 instance=null;
    //静态的工厂方法
    public static SingletonExample1 getInstance(){
        if(instance==null){
            instance=new SingletonExample1();
        }
        return instance;
    }
}
