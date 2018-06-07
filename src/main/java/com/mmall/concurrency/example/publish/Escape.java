package com.mmall.concurrency.example.publish;

import com.mmall.concurrency.annoations.NoThreadSafe;

/**
 * 对象逸出
 * 在对象没有完成发布之前，不可以将其发布
 */
@NoThreadSafe
public class Escape {
    private int thisCannBeEscape=0;
    public Escape(){
        new innerClass();
    }
    //内部类
    private class innerClass{
        public  innerClass(){
            System.out.println(Escape.this.thisCannBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }

}
