package job.xiecheng._3;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/9/21.
 */

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int[] a = new int[n1];
        for (int i = 0; i < n1; i++) {
            a[i] = sc.nextInt();
        }
        int n2 = sc.nextInt();
        int[] b = new int[n2];
        for (int i = 0; i < n2; i++) {
            b[i] = sc.nextInt();
        }
        System.out.println(getMedian(a, b, n1, n2));
    }

    public static double getMedian(int[] a, int[] b, int n1, int n2) {
        if (a == null && b == null) {
            return 0;
        }
        double mid = 0, preMid = 0;
        int len = n1 + n2;
        if (len < 2) {
            if (n1 == 0) return b[0];
            else return a[0];
        }
        int count = 0;
        int i = 0, j = 0;
        if (n1 == 0) {
            if (len % 2 == 0) {
                return ((double) b[len / 2] + (double) b[len / 2 - 1]) / 2;
            } else
                return b[len / 2];
        } else {
            if (n2 == 0) {
                if (len % 2 == 0) {
                    return ((double) a[len / 2] + (double) a[len / 2 - 1]) / 2;
                } else
                    return a[len / 2];
            }
        }
        while (count <= len / 2) {
            if ((i < n1 && j < n2 && a[i] <= b[j]) || (j >= n2)) {
                preMid = mid;
                mid = a[i];
                ++i;
            } else {
                if ((i < n1 && j < n2 && a[i] > b[j]) || (i >= n1)) {
                    preMid = mid;
                    mid = b[j];
                    ++j;
                }
            }
            ++count;
        }
        if (len % 2 == 1) {
            return mid;
        } else {
            return (mid + preMid) / 2;
        }
    }
}
