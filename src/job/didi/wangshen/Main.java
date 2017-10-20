package job.didi.wangshen;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by wangshiyi on 17/9/10.
 */

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            ArrayList<Integer> list = new ArrayList<>();
            int res = 0;
            for (int i = 0; i < n; i++) {
                list.add(sc.nextInt());
            }
            int start = 0;
            for (int i = 0; i < n; i++) {
                if (list.get(i) == 0) {
                    res++;
                    start = i + 1;
                } else {
                    for (int j = start; j < i; j++) {
                        int flag = 0;
                        for (int k = j; k <= i; k++) {
                            flag ^= list.get(k);
                        }
                        if (flag == 0) {
                            res++;
                            start = i + 1;
                        }
                    }
                }
            }
            System.out.println(res);
        }
    }
}