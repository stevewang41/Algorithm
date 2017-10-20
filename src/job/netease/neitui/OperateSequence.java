package job.netease.neitui;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/8/12.
 * <p>
 * https://www.nowcoder.com/questionTerminal/b53bda356a494154b6411d80380295f5
 */

public class OperateSequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()) {
            int n = in.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
            }
            for (int i = n - 1; i >= 0; i -= 2) {     // 前一半从最后一个数开始以2为步长递减
                System.out.print(nums[i] + " ");
            }
            for (int i = n % 2; i < n - 2; i += 2) {  // 后一半根据整数个数的奇偶，分别从第二个或第一个数开始以2为步长递增
                System.out.print(nums[i] + " ");
            }
            System.out.print(nums[n - 2]);  // 最后一个数
        }
    }
}