package job.xinyongka._2;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/9/13.
 */

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int m = in.nextInt();   // 同事数量m
            int[] h = new int[m];
            int sum = 0;
            for (int i = 0; i < m; i++) {
                h[i] = in.nextInt();
                sum += h[i];
            }
            int aSum = 0;
            for (int i = 1; i < m; i += 3) {
                aSum += h[i - 1] + h[i];

            }
            if (aSum > sum / 2) {
                System.out.println(true);
            } else {
                System.out.println(false);
            }
        }
    }

}