package job.baidu._2;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/9/19.
 */

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int n = str.length();
        int[] flag = new int[100010];
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == 'E') {
                flag[i] = 1;
            } else {
                flag[i] = 0;
            }
        }
        for (int i = 1; i < n; i++) {
            flag[i] += flag[i - 1];
        }
        int res = n - flag[n - 1];
        for (int i = 1; i < n; i++) {
            int tmp = flag[i] + n - flag[n - 1] - (i + 1 - flag[i]);
            if (tmp < res)
                res = tmp;
        }
        System.out.println(res);
    }
}
