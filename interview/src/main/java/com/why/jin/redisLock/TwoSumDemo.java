package com.why.jin.redisLock;

import java.util.HashMap;
import java.util.Map;

/**
 * description:字节跳动 两数之和
 *
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 示列：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1]
 *
 * @author whyJin
 * dateTime 2021-06-19-10-55
 */
public class TwoSumDemo {

    //遍历-->暴力破解

    /**
     * 通过双重循环遍历数组中灰姑娘所有元素的两两组合
     * 当出现符合的和时返回两个元素的下标
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        for (int i=0;i<nums.length;i++){
            for (int j=i+1;j<nums.length;j++){
                if (target - nums[i] == nums[j]){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            int partnerNumber = target - nums[i];
            if (map.containsKey(partnerNumber)){
                return new int[]{map.get(partnerNumber),i};
            }
            //map k v
            //    2 0
            map.put(nums[i],i);
        }
        return null;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{2,7,11,15};
        int target = 13;
        int[] myIndex = twoSum1(nums,target);
        for (int element: myIndex){
            System.out.println(element);
        }
    }
}
