package job.mi._1;

import java.util.Scanner;

public class Main {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static boolean fun(String table, int n) {
        String str = "";
        int count = 0;
        int max = 0;
        int i, j;
        for (i = 0; i < table.length(); i++) {
            for (j = 0; j < table.length(); j++) {
                if (table.charAt(i) == table.charAt(j)) {
                    count++;
                } else {
                    break;
                }
            }
            if (count > max) {
                max = count;
                str = table.substring(i, j);
            }
            count = 0;
        }
        int len = str.length();
        if (len == n * 2 + 1 || len == n * 2) {
            return true;
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
