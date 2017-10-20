package job.didi.wangshen;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/9/10.
 */

public class UglyNumber {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int[] ugly = new int[n];
            int index = 0;
            int i2 = 0;
            int i3 = 0;
            int i5 = 0;
            ugly[0] = 1;
            int tmp;
            while (index < n - 1) {
                tmp = Math.min(ugly[i2] * 2, Math.min(ugly[i3] * 3, ugly[i5] * 5));
                if (tmp == ugly[i2] * 2) {
                    i2++;
                }
                if (tmp == ugly[i3] * 3) {
                    i3++;
                }
                if (tmp == ugly[i5] * 5) {
                    i5++;
                }
                ugly[++index] = tmp;
            }
            System.out.println(ugly[n - 1]);

        }
    }


}
