package job.zuoyebang._2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangshiyi on 17/9/27.
 * <p>
 * 给定指定长度（约万级别）的整数数组 int[] data
 * 请设计一个函数来检查数组中是否存在两个元素的和为指定的值 sum
 * 要求函数的时间复杂度不大于NlogN，空间复杂度尽可能低
 */

public class Main {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(existTwoSum(nums, 6));
    }

    public static boolean existTwoSum(int[] nums, int target) {
        Map<Integer, Integer> elementToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 避免target - nums[i] = nums[i]的情况
            if (elementToIndex.containsKey(target - nums[i]) && elementToIndex.get(target - nums[i]) < i) {
                return true;
            }
            elementToIndex.put(nums[i], i);
        }
        return false;
    }
}