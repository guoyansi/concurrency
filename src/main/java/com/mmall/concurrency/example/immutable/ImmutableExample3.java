package com.mmall.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mmall.concurrency.annoations.ThreadSafe;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 不可更改对象
 */
@ThreadSafe
public class ImmutableExample3 {

    //private final static List<Integer> list=ImmutableList.of(1,2,3);
    private final static ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);
    private final static ImmutableSet set = ImmutableSet.copyOf(list);
//private final static ImmutableMap<Integer,Integer> map= ImmutableMap.of(1,2,3,4)
private final static ImmutableMap<Integer,Integer> map2= ImmutableMap.<Integer,Integer>builder().put(1,2).put(3,4).build();
    public static void main(String[] args) {
        //更新操作抛异常
        //list.add(4);
        //set.add(5);
        //map2.put(1,1);
        System.out.println(map2.get(1));
    }
}
