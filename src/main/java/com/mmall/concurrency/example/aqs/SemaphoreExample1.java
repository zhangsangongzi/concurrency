package com.mmall.concurrency.example.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SemaphoreExample1
{
    private final static int threadCount = 200;
    
    public static void main(String[] args) throws InterruptedException
    {
        ExecutorService exec = Executors.newCachedThreadPool();
        
        final Semaphore semaphore = new Semaphore(20);
        
        for(int i = 0; i < threadCount; i++)
        {
            final int threadNum = i;
            exec.execute(()->{
                try
                {
                    semaphore.acquire(); //获取一个许可
                    test(threadNum);
                    semaphore.release(); //释放一个许可
                }
                catch (Exception e)
                {
                    log.info("exception:",e);
                }
            });
        }
        log.info("finish");
        exec.shutdown();
    }
    
    private static void test(int threadNum) throws Exception
    {
        log.info("{}",threadNum);
        Thread.sleep(1000);
    }
}
