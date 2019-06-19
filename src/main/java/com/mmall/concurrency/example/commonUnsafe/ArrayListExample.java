package com.mmall.concurrency.example.commonUnsafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.mmall.concurrency.annoations.NotThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
public class ArrayListExample
{
    //请求总数
    public static int clientTotal = 5000;
    
    //同时并发执行的线程数
    public static volatile int threadTotal = 200;
    
    public static List<Integer> list = new ArrayList<>();
    
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
        log.info("size:{}", list.size());
    }
    
    private static void add(int i)
    {
        list.add(i);
    }
}
