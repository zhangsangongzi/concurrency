package com.mmall.concurrency.example.syncContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.hibernate.validator.internal.util.privilegedactions.NewInstance;

import com.mmall.concurrency.annoations.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class CollectionsExample2
{
    //请求总数
    public static int clientTotal = 5000;
    
    //同时并发执行的线程数
    public static volatile int threadTotal = 200;
    
    public static Set<Integer> set =Collections.synchronizedSet(new HashSet<>());
    
    public static void main(String[] args) throws Exception
    {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        
        for(int i = 0; i < clientTotal; i++)
        {
            final int count = i;
            executorService.execute(()->{
                try{
                    semaphore.acquire();
                    add(count);
                    semaphore.release();
                }catch (Exception e) {
                    log.error("Exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}", set.size());
    }
    
    private static void add(int i)
    {
         set.add(i);
    }
}