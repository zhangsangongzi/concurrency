package com.mmall.concurrency.example.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

import com.mmall.concurrency.annoations.ThreadSafe;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class AtomicExample5
{
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = 
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count"); 
    
    @Getter
    public volatile int count = 100;//volatile and not static
    
    public static void main(String[] args)
    {
        AtomicExample5 example5 = new AtomicExample5();
        if(updater.compareAndSet(example5, 100, 120))
        {
            log.info("update success1, {}", example5.getCount());
        }
        
        if(updater.compareAndSet(example5, 100, 120))
        {
            log.info("update success2, {}", example5.getCount());
        }
        else
        {
            log.info("update fail, {}", example5.getCount());
        }
    }
}
