package job.lianjia;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by wangshiyi on 17/8/19.
 * <p>
 * 去重并排序
 */

public class ArrayQC {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            set.add(in.nextInt());
        }
        in.close();
        System.out.println(set.size());
        Integer x = set.pollFirst();
        while (x != null) {
            System.out.print(x);
            x = set.pollFirst();
            if (x != null) {
                System.out.print(" ");
            }
        }
    }
}
