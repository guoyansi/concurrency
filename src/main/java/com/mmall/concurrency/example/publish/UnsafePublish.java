package com.mmall.concurrency.example.publish;

import com.mmall.concurrency.annoations.NoThreadSafe;

import java.util.Arrays;

/**
 * 发布对象
 * 对象逸出
 */
@NoThreadSafe
public class UnsafePublish {
    private String[] states={"a","b","c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish=new UnsafePublish();
        System.out.println(Arrays.toString(unsafePublish.getStates()));
        //修改数组的值
        unsafePublish.getStates()[0]="d";
        System.out.println(Arrays.toString(unsafePublish.getStates()));
    }
}
