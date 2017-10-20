package job.mi._2;

import java.util.Scanner;


/**
 * Created by wangshiyi on 17/9/18.
 */

public class Main {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static long fun(long x) {
        if (x == 1) {
            return 1;
        }
        return getYFTriangleLine(100, x);
    }

    static long getYFTriangleLine(int lines, long target) {
        long[] a = new long[lines + 1];
        long lastJLeft = 1;
        for (int i = 1; i <= lines; i++) {  // 行
            for (int j = 1; j <= i; j++) {  // 列，第i行共有i列
                long lastJ = a[j];          // 上一行，第j列的数字
                a[j] = lastJLeft + lastJ;
                if (a[j] == target) {
                    return i;
                }
                lastJLeft = lastJ;          // 保存上一行，第j-1列的数字
            }
        }
        return -1;
    }

    /******************************结束写代码******************************/


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long res;

        long _x;
        _x = Long.parseLong(in.nextLine().trim());

        res = fun(_x);
        System.out.println(String.valueOf(res));

    }
}
