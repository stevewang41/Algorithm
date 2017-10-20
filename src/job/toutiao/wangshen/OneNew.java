package job.toutiao.wangshen;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/9/10.
 */

public class OneNew {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();   // n个串
            int m = sc.nextInt();   // 判断m个连续串
            int c = sc.nextInt();   // c种颜色
            int[][] x = new int[n][];
            for (int i = 0; i < n; i++) {
                int num = sc.nextInt();
                x[i] = new int[num];
                for (int j = 0; j < num; j++) {
                    x[i][j] = sc.nextInt();  // 第i个珠子的第j种颜色
                }
            }

            int[] hasColor = new int[c];
            int[] noColor = new int[c];
            for (int i = 0; i < m && i < n; i++) {
                for (int j = 0; j < x[i].length; j++) {
                    ++hasColor[x[i][j] - 1];
                }
            }
            for (int i = 0; i < c; i++) {
                if (hasColor[i] > 1) {
                    noColor[i] = 1;
                }
            }
            for (int i = 1; i < n; i++) {
                int index1 = i - 1;
                int index2 = (i + m - 1) % n;
                for (int j = 0; j < x[index1].length; j++) {
                    --hasColor[x[index1][j] - 1];
                }
                for (int j = 0; j < x[index2].length; j++) {
                    ++hasColor[x[index2][j] - 1];
                }
                for (int j = 0; j < c; j++) {
                    if (hasColor[j] > 1) {
                        noColor[j] = 1;
                    }
                }
            }

            int res = 0;
            for (int i = 0; i < c; i++) {
                if (noColor[i] != 0) {
                    res++;
                }
            }
            System.out.println(res);
        }
    }
}