package com.mmall.concurrency.example.singleton;


import com.mmall.concurrency.annoations.NoThreadSafe;
import com.mmall.concurrency.annoations.NotRecommend;

/**
 * 懒汉模式 或者 双重同步锁单利模式
 * 单利实例在第一次使用时，进行创建
 *  使用volatile防止放生指令重拍
 */
@NoThreadSafe
@NotRecommend
public class SingletonExample5 {
    private SingletonExample5(){}

    // 1、memory = allocate() 分配对象的内存空间
    // 2、ctorInstance() 初始化对象
    // 3、instance = memory 设置instance指向刚分配的内存

    // JVM和cpu优化，发生了指令重排

    // 1、memory = allocate() 分配对象的内存空间
    // 3、instance = memory 设置instance指向刚分配的内存
    // 2、ctorInstance() 初始化对象
    //单利对象
    private static volatile SingletonExample5 instance=null;
    //静态的工厂方法
    public static SingletonExample5 getInstance(){
        if(instance==null){//双重检测机制
            synchronized (SingletonExample5.class){//同步锁
                if(instance==null){
                    instance=new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
