package job.toutiao;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/8/22.
 */

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int[] arr = new int[len];
        int curMin = Integer.MAX_VALUE;
        int curMax = Integer.MIN_VALUE;
        int curSum = 0;
        int maxProduct = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            arr[i] = in.nextInt();
            if ((arr[i] < curMin || arr[i] > curMax)){
                curSum = 0;
                curMax = arr[i];
                curMin = arr[i];
            }
            curSum += arr[i];
            maxProduct = Math.max(maxProduct, curMin * curSum);
        }

        System.out.println(maxProduct);

    }
}
