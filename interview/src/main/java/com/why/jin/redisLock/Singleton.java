package com.why.jin.redisLock;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jin
 * dateTime 2021-06-22-09:16
 */
public class Singleton {
    private static volatile Singleton instance;

    public Singleton(){

    }

    public static Singleton getInstance(){
        if (instance == null){
            synchronized (Singleton.class){
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a","b","c");

        String join = list.stream().collect(Collectors.joining(","));

        System.out.println(join);
    }

}
