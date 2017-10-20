package nowcoder.chapter4;

/**
 * Created by wangshiyi on 17/7/28.
 * <p>
 * 最大子数组和
 * 动态规划：最大子数组不可能存在累加和为负数的前缀
 * 代码本身就支持数组元素全是负数的情况
 */

public class SubArrayMaxSum {

    public static int maxSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;
        for (int i = 0; i < arr.length; i++) {
            curSum += arr[i];
            if (curSum > maxSum) {
                maxSum = curSum;
            }
            if (curSum < 0) {
                curSum = 0;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr1 = {-2, -3, -5, 40, -10, -10, 100, 1};
        System.out.println(maxSum(arr1));

        int[] arr2 = {-2, -3, -5, 0, 1, 2, -1};
        System.out.println(maxSum(arr2));

        int[] arr3 = {-2, -3, -5, -1};
        System.out.println(maxSum(arr3));

    }


}
