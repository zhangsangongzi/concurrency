package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载的时候创建
 * @author zhang
 *
 */
@ThreadSafe
public class SingletonExample6
{
    //私有构造函数
    private SingletonExample6()
    {
        
    }
    
  //单例对象 //注意静态域的顺序
    private static SingletonExample6 instance = null;
    
    static{
        instance = new SingletonExample6();
    }
    
    //private static SingletonExample6 instance = null;
    
    //静态的工厂方法
    public static SingletonExample6 getInstance()
    {
        return instance;
    }
    
    public static void main(String[] args)
    {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
