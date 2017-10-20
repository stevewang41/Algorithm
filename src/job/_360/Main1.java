package job._360;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/8/26.
 */

public class Main1 {


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int d = in.nextInt();

        int x = in.nextInt();
        int y = in.nextInt();
        int z = in.nextInt();

        double perColor = (double) z / d;
        double perWhite = (double) y / c;
        double individual = perWhite * b + perColor * a;
        int res = 0;
        if (individual >= x) {
            res += m / c * y + n / d * z;
            int white1 = m % c;
            int color1 = n % d;
            if (white1 / a > color1 / b && color1 / b > 1) {
                res += color1 / b * x;
            } else if (white1 / a < color1 / b && white1 / a > 1) {
                res += white1 / a * x;
            }
        } else if (individual < x) {
            int white1 = 0;
            int color1 = 0;
            if (n / a > m / b && m / b > 1) {
                res += m / b * x;
                white1 = m % b;
                color1 = n - m / b * a;

            } else if (n / a < m / b && n / a > 1) {
                res += n / a * x;
                white1 = m - n / a * b;
                color1 = n % a;
            }
            res += white1 / c * y;
            res += color1 / d * z;
        } else {
            int res1 = m / c * y + n / d * z;
            int white1 = m % c;
            int color1 = n % d;
            if (white1 / a > color1 / b && color1 / b > 1) {
                res1 += color1 / b * x;
            } else if (white1 / a < color1 / b && white1 / a > 1) {
                res1 += white1 / a * x;
            }

            int res2 = 0;
            int white2 = 0;
            int color2 = 0;
            if (n / a > m / b && m / b > 1) {
                res2 += m / b * x;
                white2 = m % b;
                color2 = n - m / b * a;

            } else if (n / a < m / b && n / a > 1) {
                res2 += n / a * x;
                white2 = m - n / a * b;
                color2 = n % a;
            }
            res2 += white1 / c * y;
            res2 += color1 / d * z;
            res = Math.max(res1, res2);
        }


        System.out.println(res);
    }
}