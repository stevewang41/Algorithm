package job.didi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by wangshiyi on 17/8/26.
 */

public class SubMaxSum {

    public static int maxSum(List<Integer> list) {
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;
        for (int i = 0; i < list.size(); i++) {
            curSum += list.get(i);
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
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        while (in.hasNextInt()) {//注意while处理多个case
            list.add(in.nextInt());
        }
        System.out.println(maxSum(list));
    }
}