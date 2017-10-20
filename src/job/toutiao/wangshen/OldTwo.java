package job.toutiao.wangshen;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by wangshiyi on 17/9/10.
 */

public class OldTwo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            HashMap<Integer, String> map = new HashMap<>();
            int n = sc.nextInt();   // 用户的个数
            for (int i = 0; i < n; i++) {
                int u = sc.nextInt();    // 用户i对文章的喜好度
                if (map.containsKey(u)) {
                    String[] str = map.get(u).split("_");
                    map.put(u, str[0] + "_" + i);
                } else {
                    map.put(u, i + "_" + i);
                }
            }
            int q = sc.nextInt();   // 查询的组数
            int[] l = new int[q];
            int[] r = new int[q];
            int[] k = new int[q];
            for (int i = 0; i < q; i++) {
                l[i] = sc.nextInt();
                r[i] = sc.nextInt();
                k[i] = sc.nextInt();
            }

            for (int i = 0; i < q; i++) {
                int res;
                String[] str = map.get(k[i]).split("_");
                int left = Integer.parseInt(str[0]);
                int right = Integer.parseInt(str[1]);
                if (r[i] - 1 < left || l[i] - 1 > right) {
                    res = 0;
                } else if (r[i] - 1 >= left) {
                    res = Math.min(r[i] - 1, right) - left + 1;
                } else {
                    res = right - Math.max(l[i] - 1, left);
                }
                System.out.println(res);
            }
        }
    }
}
