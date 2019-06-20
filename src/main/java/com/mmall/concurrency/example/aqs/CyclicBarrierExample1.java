package com.mmall.concurrency.example.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.remoting.support.RemoteInvocationTraceInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CyclicBarrierExample1
{
    private static CyclicBarrier barrier = new CyclicBarrier(5);
    
    public static void main(String[] args) throws InterruptedException
    {
        ExecutorService exec = Executors.newCachedThreadPool();
        
        for(int i = 0; i < 10; i++)
        {
            final int threadNum = i;
            Thread.sleep(1000);
            exec.execute(()->{
                try
                {
                    race(threadNum);
                }
                catch (Exception e)
                {
                    log.info("exception",e);
                }
            });
        }
        exec.shutdown();
    }
    
    private static void race(int threadNum) throws Exception
    {
        Thread.sleep(1000);
        log.info("{} is ready",threadNum);
        try{
            barrier.await(2000,TimeUnit.MILLISECONDS);
        }
        catch(Exception e){
            log.warn("",e);
        }
        log.info("{} continue",threadNum);
    }
}
