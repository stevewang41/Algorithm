package job.netease.wangshen._1;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/9/9.
 * <p>
 * 游历魔法王国
 * https://www.nowcoder.com/question/next?pid=6910869&qid=126952&tid=10892573
 */

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();   // 城市个数
            int L = in.nextInt();   // 小易能行动的次数
            int[] parent = new int[n - 1];
            for (int i = 0; i <= n - 2; i++) {
                parent[i] = in.nextInt();
            }
            int maxLen = findMaxLen(0, 0, parent);
            int res;
            if (L <= maxLen) {
                res = L + 1;
            } else {
                res = maxLen + (L - maxLen) / 2 + 1;
            }
            System.out.println(res);
        }
    }

    private static int findMaxLen(int c, int len, int[] p) {
        int maxLen = len;
        for (int i = 1; i <= p.length; i++) {
            if (p[i - 1] == c) {
                int tmp = findMaxLen(i, len + 1, p);
                if (tmp > maxLen) {
                    maxLen = tmp;
                }
            }
        }
        return maxLen;
    }
}