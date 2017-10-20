package job.baidu._1;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/9/19.
 */

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        n++;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        m++;
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = sc.nextInt();
        }
        if (n <= m) {
            String res = solve(a, n, b, m);
            System.out.println(res);

        } else {
            String res = solve(b, m, a, n);
            if (res == ">")
                System.out.println("<");
            else
                System.out.println(">");
        }

    }

    public static String solve(int a[], int n, int b[], int m) {
        String res = "=";
        int i;
        for (i = 0; i < n; i++) {
            if (a[i] == b[i]) continue;
            if (a[i] > b[i]) {
                if (i % 2 == 1)
                    res = "<";
                else
                    res = ">";
                break;
            } else {
                if (i % 2 == 1)
                    res = ">";
                else
                    res = "<";
                break;
            }
        }
        if (i == n - 1 && m == n + 1)
            if (a[n] == b[n] + 1 && b[m] == 1)
                res = "=";
        if (res == "=" && i >= n) {
            if (m > n) {
                if (n % 2 == 1)
                    res = "<";
                else
                    res = ">";
            }
        }
        return res;
    }
}
