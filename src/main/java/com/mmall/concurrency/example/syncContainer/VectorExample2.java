package com.mmall.concurrency.example.syncContainer;

import java.util.Vector;
import com.mmall.concurrency.annoations.NotThreadSafe;

@NotThreadSafe
public class VectorExample2
{
    public static Vector<Integer> vector = new Vector<>();
    
    public static void main(String[] args) throws Exception
    {
        
        while(true){
            for(int i = 0; i < 10; i++)
            {
                vector.add(i);
            }
            
            Thread thread1 = new Thread(){
                public void run()
                {
                    for(int i = 0; i < vector.size(); i++)
                    {
                        vector.remove(i);
                    }
                }
            };
            
            Thread thread2 = new Thread(){
                public void run()
                {
                    for(int i =0; i < vector.size(); i++)
                    {
                        vector.get(i);
                    }
                }
            };
            thread1.start();
            thread2.start();
        }
    }
}
