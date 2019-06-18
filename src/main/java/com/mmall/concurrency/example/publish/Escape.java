package com.mmall.concurrency.example.publish;

import com.mmall.concurrency.annoations.NotRecommend;
import com.mmall.concurrency.annoations.NotThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotRecommend
//对象未完成构造之前不可发布
public class Escape
{
    private int thisCanBeEscape = 0;
    
    public Escape()
    {
        new InnerClass();
    }
    
    private class InnerClass
    {
        public InnerClass()
        {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }
    
    public static void main(String[] args)
    {
        new Escape();
    }
}
