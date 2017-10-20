package douyu.douyu_2017_07_24;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/7/29.
 * <p>
 * 给定一个全是数字的字符串，返回可以转成合法IP的数量
 * 例如：101111
 * 可以转成：
 * 1.0.1.111, 1.0.11.11, 1.0.111.1
 * 10.1.1.11, 10.1.11.1, 10.11.1.1
 * 101.1.1.1
 * 所以返回7
 */

public class ConvertStringToIP {

    public static int convertToIPNum1(String str) {
        if (str == null || str.length() < 4 || str.length() > 12) {
            return 0;
        }
        char[] chas = str.toCharArray();
        return process(chas, 0, 0);
    }

    /**
     * 递归求解函数
     *
     * @param chas
     * @param i
     * @param parts i之前的字符已经组成了parts个IP段
     * @return 返回整个字符串可以转成合法IP的数量
     */
    public static int process(char[] chas, int i, int parts) {
        if (i > chas.length || parts > 4) {      // base case 不依赖任何子过程
            return 0;
        }
        if (i == chas.length) {         // 终止位置
            return parts == 4 ? 1 : 0;  // 前面的数字构成了4个IP段，为合法IP
        }
        int res = process(chas, i + 1, parts + 1);  // 当前数字chas[i]自己作为一个IP段，看看能构成多少个合法IP
        if (chas[i] == '0') {   // 如果当前数字是0，只能够把自己作为一个IP段，没有额外的可能性
            return res;         // 直接返回上面算出的合法IP数
        }
        res += process(chas, i + 2, parts + 1); // 如果当前数字不是0，chas[i]和chas[i+1]一定可以一起作为一个IP段
        if (i + 2 < chas.length) {
            int sum = (chas[i] - '0') * 100 + (chas[i + 1] - '0') * 10 + (chas[i + 2] - '0');
            if (sum < 256) {    // chas[i]、chas[i+1]以及chas[i + 2]可以一起作为一个IP段
                return res + process(chas, i + 3, parts + 1);
            } else {
                return res;
            }
        } else {
            return res;
        }
    }

    /**
     * 由递归转成的动态规划，套路固定
     * 详见：http://blog.csdn.net/qq_24028753/article/details/76149135
     *
     * @param str
     * @return
     */
    public static int convertToIPNum2(String str) {
        if (str == null || str.length() < 4 || str.length() > 12) {
            return 0;
        }
        char[] chas = str.toCharArray();
        int size = chas.length;
        int[][] dp = new int[size + 3][5];
        dp[size][4] = 1;
        for (int parts = 3; parts >= 0; parts--) {
            for (int i = Math.min(size - 1, parts * 3); i >= parts; i--) {
                dp[i][parts] = dp[i + 1][parts + 1];
                if (chas[i] != '0') {
                    dp[i][parts] += dp[i + 2][parts + 1];
                    if (i + 2 < chas.length) {
                        int sum = (chas[i] - '0') * 100 + (chas[i + 1] - '0') * 10 + (chas[i + 2] - '0');
                        if (sum < 256) {
                            dp[i][parts] += dp[i + 3][parts + 1];
                        }
                    }
                }
            }
        }
        return dp[0][0];
    }

    public static String getRandomNumberString() {
        char[] chas = new char[(int) (Math.random() * 10) + 3];
        for (int i = 0; i < chas.length; i++) {
            chas[i] = (char) (48 + (int) (Math.random() * 10));
        }
        return String.valueOf(chas);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testTime = 3000000;
        boolean hasErr = false;
        for (int i = 0; i < testTime; i++) {
            String test = getRandomNumberString();
            if (convertToIPNum1(test) != convertToIPNum2(test)) {
                hasErr = true;
            }
        }
        if (hasErr) {
            System.out.println("233333");
        } else {
            System.out.println("666666");
        }

    }
}
