package job.toutiao.wangshen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by wangshiyi on 17/9/10.
 * <p>
 * 5
 * 1 2 3 3 5
 * 3
 * 1 2 1
 * 2 4 5
 * 3 5 3
 */

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = in.nextInt();       // 用户的个数
        int[] u = new int[n + 1];   // 用户i对文章的喜好度
        for (int i = 1; i <= n; i++) {
            u[i] = in.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            int like = u[i];
            if (map.containsKey(like)) {
                map.get(like).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(like, list);
            }
        }

        int q = in.nextInt();   // 查询的组数
        for (int i = 0; i < q; i++) {
            int l = in.nextInt();
            int r = in.nextInt();
            int k = in.nextInt();
            int res = 0;
            if (!map.containsKey(k)) {
                System.out.println(0);
            } else {
                List<Integer> list = map.get(k);
                for (int index : list) {
                    if (index >= l && index <= r) {
                        ++res;
                    }
                }
                System.out.println(res);
            }
        }
    }
}
