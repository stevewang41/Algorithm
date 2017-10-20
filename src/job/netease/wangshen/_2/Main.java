package job.netease.wangshen._2;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/9/9.
 */

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            int t = sc.nextInt();
            for (int i = 0; i < t; i++) {
                int len = sc.nextInt();
                int[] arr = new int[len];
                for (int j = 0; j < len; j++) {
                    arr[j] = sc.nextInt();
                }
                String res;
                int num1 = 0;
                int num4 = 0;
                for (int j = 0; j < arr.length; j++) {
                    if (arr[j] % 2 != 0) {
                        ++num1;
                    } else if (arr[j] % 4 == 0) {
                        ++num4;
                    }
                }
                if (num1 <= num4) {
                    res = "Yes";
                } else {
                    res = "No";
                }
                System.out.println(res);
            }
        }
    }
}