package job.sohu;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/8/28.
 */

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int n = in.nextInt();
            int m = in.nextInt();
            int[] a = new int[m];
            for (int i = 0; i < m; i++) {
                a[i] = in.nextInt();
            }

            int i = 0;
            int j = 0;
            int k = 0;
            int [] b = new int[n];
            int times;
            int num;
            while (j < n) {
                b[j] = a[i];
                System.out.println(b[j]);
                times = b[k];
                num = a[i];
                while (--times > 0) {
                    b[++j] = num;
                    System.out.println(b[j]);
                    if (j >= n) {
                        break;
                    }
                }
                k = i + 1;
                i = k % m;
                j++;
            }
        }
    }
}
