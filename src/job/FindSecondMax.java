package job;

/**
 * Created by wangshiyi on 17/9/18.
 */

public class FindSecondMax {

    public static void main(String[] args) {
        int[] arr = {1, 6, 3, 4, 5, 2, 7};
        int max1 = 0;
        int max2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max1) {
                max2 = max1;
                max1 = arr[i];
            } else if (arr[i] > max2 && arr[i] < max1) {
                max2 = arr[i];
            }
        }
        System.out.println("第二大的数是：" + max2);
    }
}
