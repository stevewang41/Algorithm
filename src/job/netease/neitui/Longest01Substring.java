package job.netease.neitui;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/8/12.
 *
 * https://www.nowcoder.com/questionTerminal/3fbd8fe929ea4eb3a254c0ed34ac993a
 */

public class Longest01Substring {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String str = in.next();
            int maxLen = 1;
            int len = 1;
            for (int i = 1; i < str.length(); i++) {
                if (str.charAt(i - 1) != str.charAt(i)) {
                    len++;
                    if (len > maxLen) {
                        maxLen = len;
                    }
                } else {
                    len = 1;
                }
            }
            System.out.println(maxLen);
        }
    }
}