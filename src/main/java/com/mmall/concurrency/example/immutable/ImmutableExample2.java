package com.mmall.concurrency.example.immutable;

import com.mmall.concurrency.annoations.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class ImmutableExample2 {

    private static Map<Integer,Integer> map=new HashMap<Integer, Integer>();
    static {
        map.put(1,2);
        /**
         * 被unmodifiableMap处理过的map是不允许被修改的
         */
        map= Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        //a=2;
        //b="3";
        /**
         * final修饰的引用类型不允许重写
         * 但是允许修改和添加
         */
        //map=new HashMap<Integer, Integer>();
        map.put(3,5);
        System.out.println(map.get(3));
    }
    private  void  test(final int a){
        //编译报错
        //a=1;
    }
}
