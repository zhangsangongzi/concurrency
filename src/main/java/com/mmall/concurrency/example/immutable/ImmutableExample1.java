package com.mmall.concurrency.example.immutable;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImmutableExample1
{
    private final static Integer a = 0;
    private final static String b = "b";
    private final static Map<Integer, Integer> map = new HashMap<>();
    
    static
    {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }
    
    public static void main(String[] args)
    {
//        a = 1;
//        b = "c";
//        map = new HashMap<>();
        map.put(1, 3);
        log.info("{}",map.get(1));
    }
}
