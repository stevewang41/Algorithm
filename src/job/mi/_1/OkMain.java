package job.mi._1;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/9/20.
 */

public class OkMain {

    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static boolean fun(String table, int n) {
        char[] seats = table.toCharArray();
        if (n <= 0) {
            return true;
        } else if (seats.length == 0) {
            return false;
        }
        int i = 0;
        while (i < seats.length - 1) {
            if (seats[i] == '1') {   // 1*
                i += 2;
            } else if (i == 0 && seats[i + 1] == '0') {    // 00*
                n--;
                i += 2;
            } else if (i > 0 && seats[i + 1] == '0' && seats[i - 1] == '0') { // *000*
                n--;
                i += 2;
            } else {
                i++;
            }
            if (n == 0) {
                return true;
            }
        }
        return false;
    }

    /******************************结束写代码******************************/


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean res;

        String _table;
        try {
            _table = in.nextLine();
        } catch (Exception e) {
            _table = null;
        }

        int _n;
        _n = Integer.parseInt(in.nextLine().trim());

        res = fun(_table, _n);
        System.out.println(String.valueOf(res ? 1 : 0));
    }
}
