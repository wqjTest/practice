package com.why.jin.juc.completableFuture;


import lombok.Getter;
import sun.nio.ch.Net;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * (1需求：
 *   1.1：同一款产品，同时搜索出同款产品在各大电商平台的售价
 *   1.2：同款产品，同时搜索出本产品在同一个电商平台下，各个入驻卖家售价时多少
 *  2输出：出来结果希望时同款产品的在不同地方的价格清单列表，返回一个List<String>
 *      《mysql》in jd price is 88.05
 *      《mysql》in dangdang price is 86.11
 *      《mysql》in taobao price is 90.43
 *  3技术要求
 *    3.1 函数式编程
 *    3.2 链式编程
 *    3.3 Stream流式计算
 * )
 *
 * <p>
 * 修改历史:                                 <br>
 * 修改日期           修改人员       版本       修改内容<br>
 * -------------------------------------------------<br>
 * 2022-8-9 9:17   qianjin.wang     1.0       初始化创建<br>
 * </p>
 *
 * @author qianjin.wang
 * @version 1.0
 * @since JDK1.8
 */
public class CompletableFutureMallDemo {

    static List<NetMall> list = Arrays.asList(new NetMall("jd"),new NetMall("dangdang"),new NetMall("taobao"));

    /**
     * step by step 一家家搜查
     * @param list
     * @param productName
     * @return
     */
    public static List<String> getPrice(List<NetMall> list,String productName) {
      return  list.stream().map(m -> {
           return String.format(productName + "in %s price is %.2f",m.getNetMallName(),m.calcPrice(productName));
        }).collect(Collectors.toList());
    }


    public static List<String> getPriceByCompletableFuture(List<NetMall> list,String productName) {
        return list.stream().map(netMall ->
                CompletableFuture.supplyAsync(() ->String.format(productName + "in %s price is %.2f",netMall.getNetMallName(),netMall.calcPrice(productName)))).collect(Collectors.toList())
                .stream().map(s -> s.join()).collect(Collectors.toList());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();

        List<String> list1 = getPrice(CompletableFutureMallDemo.list, "mysql");

        for (String element: list1){
            System.out.println(element);
        }

        long end = System.currentTimeMillis();
        System.out.println("---costTime:" + (end-start) + "毫秒");

        System.out.println("--------------------------------");

        long start2 = System.currentTimeMillis();

        List<String> list2 = getPriceByCompletableFuture(CompletableFutureMallDemo.list, "mysql");

        for (String element: list2){
            System.out.println(element);
        }

        long end2 = System.currentTimeMillis();
        System.out.println("---costTime:" + (end2-start2) + "毫秒");
    }

    /** join和get的对比 */
    public void future() throws ExecutionException, InterruptedException {
        CompletableFuture<Object> completableFuture = CompletableFuture.supplyAsync(() ->{
            return "hello 1234";
        });

        System.out.println(completableFuture.get());

        System.out.println(completableFuture.join());
    }
}

class NetMall{
    @Getter
    private String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    public double calcPrice(String productName){
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {throw new RuntimeException(e);}
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}



