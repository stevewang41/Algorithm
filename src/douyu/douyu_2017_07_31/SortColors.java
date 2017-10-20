package douyu.douyu_2017_07_31;

/**
 * Created by wangshiyi on 17/8/1.
 *
 * 荷兰国旗问题
 * 利用快速排序partition过程的思想
 * 时间复杂度O(n)，空间复杂度O(1)
 */

public class SortColors {

    public static void main(String[] args) {

        int[] nums = {0, 1, 2, 1, 1, 2, 0, 2, 1, 0};
        System.out.print("原数组：");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        sortColors(nums);
        System.out.println();
        System.out.print("排序后：");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    /**
     * 利用快速排序partition过程的思想
     *
     * @param nums
     */
    public static void sortColors(int[] nums) {
        int begin = 0;
        int end = nums.length - 1;
        int cur = 0;
        while (cur <= end) {
            if (nums[cur] < 1) {
                swapElement(nums, begin, cur);
                begin++;
                cur++;  // 从begin位置交换过来的元素一定是已经遍历过的元素
            } else if (nums[cur] > 1) {
                swapElement(nums, end, cur);
                end--;
                // 从end位置交换过来的元素我们并没有遍历过
            } else {
                cur++;
            }
        }
    }

    public static void swapElement(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

