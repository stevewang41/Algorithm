package job;

/**
 * Created by wangshiyi on 17/9/20.
 *
 * 递归输出全排列
 */

public class QuanPaiLie {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        perm(arr, 0, arr.length - 1);
    }

    private static void perm(int[] arr, int start, int end) {
        if (start == end) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i]);
            }
            System.out.println();
        } else {
            for (int i = start; i <= end; i++) {
                swap(arr, start, i);    // 把arr[i]放在首部
                perm(arr, start + 1, end);  // 递归求i+1
                swap(arr, start, i);    // 还原数组
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
