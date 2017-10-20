package douyu.douyu_2017_08_05;

/**
 * Created by wangshiyi on 17/8/6.
 * <p>
 * P388  给定一个整形数组，返回排序后的相邻两数的最大差值，要求时间复杂度O(n)
 */

public class ArrayMaxGap {

    /**
     * 映射函数，将数组元素映射到桶中，使用long类型防止相乘时溢出
     *
     * @param num
     * @param n
     * @param min
     * @param max
     * @return
     */
    public static int mapToBucket(long num, long n, long min, long max) {
        return (int) ((num - min) * n / (max - min));
    }

    public static int getMaxGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) { // 找到数组的最小值和最大值
            if (nums[i] < min) {
                min = nums[i];
            }
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        if (min == max) {
            return 0;
        }
        boolean[] hasNum = new boolean[n + 1]; // 标识桶中是否有数
        int[] bMins = new int[n + 1];          // 桶中最小值
        int[] bMaxs = new int[n + 1];          // 桶中最大值
        for (int i = 0; i < n; i++) {          // 统计每个桶中的最小值和最大值
            int b = mapToBucket(nums[i], n, min, max);  // 当前元素所在的桶
            if (hasNum[b]) {
                if (nums[i] < bMins[b]) {
                    bMins[b] = nums[i];
                }
                if (nums[i] > bMaxs[b]) {
                    bMaxs[b] = nums[i];
                }
            } else {
                bMins[b] = bMaxs[b] = nums[i];
                hasNum[b] = true;
            }
        }
        int maxGap = 0;
        int lastbMax = bMaxs[0];    // 前一个非空桶的最大值，第一个非空桶必是0号桶
        int b = 1;
        while (b <= n) {
            if (hasNum[b]) {
                if (bMins[b] - lastbMax > maxGap) { // 当前非空桶的最小值与上一个非空桶的最大值做差，取最大差值
                    maxGap = bMins[b] - lastbMax;
                }
                lastbMax = bMaxs[b];
            }
            b++;
        }
        return maxGap;
    }


    public static void main(String[] args) {

        int[] arr = new int[]{9, 3, 1, 10};
        System.out.println(getMaxGap(arr));
    }
}