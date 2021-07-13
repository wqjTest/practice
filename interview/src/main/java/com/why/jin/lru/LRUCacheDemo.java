package com.why.jin.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Jin
 * dateTime 2021-07-13-14:07
 */
public class LRUCacheDemo<K, V> extends LinkedHashMap {

    //缓存坑位
    private int capacity;

    /**
     *
     * <tt>true</tt> for access-order,
     * <tt>false</tt> for insertion-order
     */
    public LRUCacheDemo(int capacity) {
        super(capacity,0.75F,false);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return super.size() > capacity;
    }

    public static void main(String[] args) {
        LRUCacheDemo lruCacheDemo = new LRUCacheDemo(3);

        lruCacheDemo.put(1, "a");
        lruCacheDemo.put(2, "b");
        lruCacheDemo.put(3, "c");

        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(4,"d");
        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(3,"c");
        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(3,"c");
        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(3,"c");
        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(5,"x");
        System.out.println(lruCacheDemo.keySet());

    }

    /**
     * true
     *
     * [1, 2, 3]
     * [2, 3, 4]
     * [2, 4, 3]
     * [2, 4, 3]
     * [2, 4, 3]
     * [4, 3, 5]
     */

    /**
     * false
     *
     * [1, 2, 3]
     * [2, 3, 4]
     * [2, 3, 4]
     * [2, 3, 4]
     * [2, 3, 4]
     * [3, 4, 5]
     */
}
