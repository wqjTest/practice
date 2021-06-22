package com.why.jin;


import com.google.common.collect.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * description:
 *
 * @author Jin
 * dateTime 2021-06-22-09:42
 */
public class StringUtil {
    //1 Java自带工具方法

    /**
     * List集合拼接成以逗号分隔的字符串
     */
    @Test
    public void demo1() {
        // 如何把list集合拼接成以逗号分隔的字符串 a,b,c
        List<String> list = Arrays.asList("a", "b", "c");
        // 第一种方法，可以用stream流
        String join = list.stream().collect(Collectors.joining(","));

        System.out.println(join);

        // 第二种方法，其实String也有join方法可以实现这个功能
        String join2 = String.join(",", list);
        System.out.println(join2);
    }

    /**
     * 比较两个字符串是否相等，忽略大小写
     */
    @Test
    public void demo2() {
        String s1 = "aBc";
        String s2 = "AbC";
        if (s1.equalsIgnoreCase(s2)) {
            System.out.println("相等");
        }
    }

    /**
     * 比较两个对象是否相等
     * description:
     * 当我们用equals比较两个对象是否相等的时候，还需要对左边的对象进行判空，
     * 不然可能会报空指针异常，我们可以用java.util包下Objects封装好的比较是否相等的方法
     */
    @Test
    public void demo3() {
        String strA = "aBc";
        String strB = "AbC";
        System.out.println(Objects.equals(strA, strB));
    }

    /**
     * 两个List集合取交集
     */
    @Test
    public void demo4() {
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("b");
        list2.add("d");
        list1.retainAll(list2);
        System.out.println(list1);
    }

    //2 apache commons工具类库

    /**
     * 首字母转成大写
     */
    @Test
    public void demo5() {
        String str = "suzhou";
        String capitalize = StringUtils.capitalize(str);
        System.out.println(capitalize);
    }

    /**
     * 重复拼接字符串
     */
    @Test
    public void demo6() {
        String str = StringUtils.repeat("ab", 3);
        System.out.println(str);
    }

    /**
     * 格式化日期
     */
    @Test
    public void demo7() {
        // Date类型转String类型
        String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(date); // 输出 2021-05-01 01:01:01

        // String类型转Date类型
        try {
            Date date1 = DateUtils.parseDate("2021-05-01 01:01:01", "yyyy-MM-dd HH:mm:ss");
            System.out.println("date1:" + date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 计算一个小时后的日期
        Date date2 = DateUtils.addHours(new Date(), 1);
        System.out.println("date2:" + date2);
    }

    /**
     * 包装临时对象
     * description:
     * 当一个方法需要返回两个及以上字段时，
     * 我们一般会封装成一个临时对象返回，
     * 现在有了Pair和Triple就不需要了
     */
    @Test
    public void demo8() {
        // 返回两个字段
        ImmutablePair<Integer, String> pair = ImmutablePair.of(1, "suzhou");
        // 输出 1,suzhou
        System.out.println(pair.getLeft() + "," + pair.getRight());
        // 返回三个字段
        ImmutableTriple<Integer, String, Date> triple = ImmutableTriple.of(1, "yideng", new Date());
        System.out.println(triple.left + "," + triple.getMiddle() + "," + triple.right);
    }

    //3 commons-collections 集合工具类

    /**
     * 集合判空
     */
    @Test
    public void demo9() {
        List<String> listA = Arrays.asList("1","3");
        List<String> listB = Arrays.asList("1");

        // 两个集合取交集
        Collection<String> collection1 = CollectionUtils.retainAll(listA, listB);
        // 两个集合取并集
        Collection<String> collection2 = CollectionUtils.union(listA, listB);
        // 两个集合取差集
        Collection<String> collection3 = CollectionUtils.subtract(listA, listB);
    }

    //4 common-beanutils 操作对象
    /**
     * 设置对象属性
     * 对象和map互转
     */
    @Test
    public void demo10() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        User user = new User();
        BeanUtils.setProperty(user,"id",1);
        BeanUtils.setProperty(user,"name","suzhou");
        System.out.println(BeanUtils.getProperty(user,"name"));
        System.out.println(user);

        //对象转map
        Map<String,String> map = BeanUtils.describe(user);
        //map转对象
        User newUser = new User();
        BeanUtils.populate(newUser,map);
    }

    //5 commons-io 文件流处理
    /**
     * 文件处理
     */
    @Test
    public void demo11(){
        File file = new File("demo1.txt");
        //读取文件
        try {
            List<String> lines = FileUtils.readLines(file, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //写入文件
        //FileUtils.writeLines(new File("demo2.txt"), lines);
        //复制文件
        //FileUtils.copyFile(srcFile,destFile);
    }

    //6 Google Guava 工具类库
    /**
     * 创建集合
     */
    @Test
    public void demo12(){
        List<String> list = Lists.newArrayList();
        List<Integer> list2 = Lists.newArrayList(1, 2, 3);
        // 反转list
        List<Integer> reverse = Lists.reverse(list2);
        System.out.println(reverse); // 输出 [3, 2, 1]
        // list集合元素太多，可以分成若干个集合，每个集合10个元素
        List<List<Integer>> partition = Lists.partition(list2, 10);

        Map<String, String> map = Maps.newHashMap();
        Set<String> set = Sets.newHashSet();
    }

    /**
     * Multimap 一个key可以映射多个value的HashMap
     */
    @Test
    public void demo13(){
        Multimap<String,Integer> map = ArrayListMultimap.create();
        map.put("key",1);
        map.put("key",2);
        Collection<Integer> values = map.get("key");
        System.out.println(map);
        System.out.println(values);

        // 还能返回你以前使用的臃肿的Map
        Map<String, Collection<Integer>> collectionMap = map.asMap();
        System.out.println(collectionMap);
    }

    /**
     * BiMap 一种连value也不能重复的HashMap
     */
    @Test
    public void demo14(){
        BiMap<String, String> biMap = HashBiMap.create();
        // 如果value重复，put方法会抛异常，除非用forcePut方法
        biMap.put("key","value");
        System.out.println(biMap); // 输出 {"key":"value"}
        // 既然value不能重复，何不实现个翻转key/value的方法，已经有了
        BiMap<String, String> inverse = biMap.inverse();
        System.out.println(inverse); // 输出 {"value":"key"}
    }


    /**
     * Table 一种有两个key的HashMap
     */
    @Test
    public void demo15(){
        // 一批用户，同时按年龄和性别分组
        Table<Integer, String, String> table = HashBasedTable.create();
        table.put(18, "男", "yideng");
        table.put(18, "女", "Lily");
        System.out.println(table.get(18, "男")); // 输出 yideng
        // 这其实是一个二维的Map，可以查看行数据
        Map<String, String> row = table.row(18);
        System.out.println(row); // 输出 {"男":"yideng","女":"Lily"}
        // 查看列数据
        Map<Integer, String> column = table.column("男");
        System.out.println(column); // 输出 {18:"yideng"}
    }

    /**
     * Multiset 一种用来计数的Set
     */
    @Test
    public void demo16(){
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("apple");
        multiset.add("apple");
        multiset.add("orange");
        System.out.println(multiset.count("apple")); // 输出 2
        // 查看去重的元素
        Set<String> set = multiset.elementSet();
        System.out.println(set); // 输出 ["orange","apple"]
        // 还能查看没有去重的元素
        Iterator<String> iterator = multiset.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        // 还能手动设置某个元素出现的次数
        multiset.setCount("apple", 5);
    }

}


class User {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
