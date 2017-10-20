package job.mi._3;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/9/18.
 */


public class Main {


    /**
     * 统计出最多的那个饮料，根据间隔时间算出喝完最多的饮料要多久
     * 注意可能有多个同样都是最多的，每多一个就得加一，得出来的这个最久和所有饮料的总数比较，返回大的。
     *
     * @param drinks
     * @param n
     * @return
     */
    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static int fun(String drinks, int n) {
        int[] num = new int[7]; // 记录7种饮料各自的数量
        int maxNum = 0;         // 同种饮料的最大数量
        int sameMaxNum = 0;     // 同为最大的饮料种数
        int len = drinks.length();
        for (int i = 0; i < len; i++) {
            if (drinks.charAt(i) == 'A') {
                num[0]++;
            } else if (drinks.charAt(i) == 'B') {
                num[1]++;
            } else if (drinks.charAt(i) == 'C') {
                num[2]++;
            } else if (drinks.charAt(i) == 'D') {
                num[3]++;
            } else if (drinks.charAt(i) == 'E') {
                num[4]++;
            } else if (drinks.charAt(i) == 'F') {
                num[5]++;
            } else if (drinks.charAt(i) == 'G') {
                num[6]++;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (num[i] > maxNum) {  // 找到数量更多的饮料
                maxNum = num[i];    // 最大
                sameMaxNum = 0;
            } else if (num[i] == maxNum) {
                sameMaxNum++;
            }
        }
        int res = maxNum + (maxNum - 1) * n + sameMaxNum;
        return Math.max(res, len);
    }


    /******************************结束写代码******************************/


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int res;

        String _drinks;
        try {
            _drinks = in.nextLine();
        } catch (Exception e) {
            _drinks = null;
        }

        int _n;
        _n = Integer.parseInt(in.nextLine().trim());

        res = fun(_drinks, _n);
        System.out.println(String.valueOf(res));

    }
}
