package com.mmall.concurrency.example.commonUnsafe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.mmall.concurrency.annoations.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class DateFormatExample2
{
    //private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmdd");
    
    //请求总数
    public static int clientTotal = 5000;
    
    //同时并发执行的线程数
    public static volatile int threadTotal = 200;
   
    public static void main(String[] args) throws Exception
    {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        
        for(int i = 0; i < clientTotal; i++)
        {
            executorService.execute(()->{
                try{
                    semaphore.acquire();
                    update();
                    semaphore.release();
                }catch (Exception e) {
                    log.error("Exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }
    
    private static void update()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmdd");
        try
        {
            simpleDateFormat.parse("20190619");
        }
        catch (ParseException e)
        {
            log.error("prase exception!", e);
        }
    }
}
