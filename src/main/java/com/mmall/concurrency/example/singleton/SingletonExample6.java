package com.mmall.concurrency.example.singleton;


import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单利实例在类装载的时候，进行创建
 * 如果这个单例没有被使用，则会造成资源的浪费
 */
@ThreadSafe
public class SingletonExample6 {
    private SingletonExample6(){}
    //单利对象
    private static SingletonExample6 instance=null;

    static {
        instance=new SingletonExample6();
    }
    //单利对象
   // private static SingletonExample6 instance=null;
    //静态的工厂方法
    public static SingletonExample6 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance());
        System.out.println(getInstance());
    }
}
