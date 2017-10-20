package nowcoder.chapter2;

/**
 * Created by wangshiyi on 17/7/26.
 *
 * 数组小和（单调和），利用归并排序，在对有序子数组进行merge的同时，累加小和
 * 类似的另一题：数组逆序对（左边比右边大的对数）
 */

public class ArraySmallSum {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 2, 4, 6};
        System.out.println(getSmallSum(arr));
    }


    public static int getSmallSum(int[] arr) {

        return mergeSortRecursion(arr, 0, arr.length - 1);
    }

    /**
     * 递归实现归并排序
     *
     * @param arr
     * @param l
     * @param r
     * @return 返回数组小和
     */
    public static int mergeSortRecursion(int[] arr, int l, int r) {
        if (l == r) {   // 当待排序数组长度为1时，递归开始回溯，进行merge操作
            return 0;
        }
        int mid = (l + r) / 2;
        return mergeSortRecursion(arr, l, mid) + mergeSortRecursion(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    /**
     * 合并两个已排好序的数组s[left...mid]和s[mid+1...right]
     *
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @return 返回合并过程中累加的数组小和
     */
    public static int merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];    // 辅助存储空间 O(n)
        int index = 0;
        int i = left;
        int j = mid + 1;
        int smallSum = 0;       // 新增，用来累加数组小和
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                // 当前一个数组元素小于或等于后一个数组元素时，累加小和
                // s[i] <= s[j] -> s[i] <= s[j]...s[right]
                smallSum += arr[i] * (right - j + 1);
                temp[index++] = arr[i++];
            } else {
                temp[index++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[index++] = arr[i++];
        }
        while (j <= right) {
            temp[index++] = arr[j++];
        }
        for (int k = 0; k < temp.length; k++) {
            arr[left++] = temp[k];
        }
        return smallSum;
    }
}
