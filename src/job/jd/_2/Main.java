package job.jd._2;

import java.util.Scanner;
import java.util.Vector;

/**
 * Created by wangshiyi on 17/9/8.
 */

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        int r = sc.nextInt();
        int temp;
        int count = 0;

        for (int i = l; i <= r; i++) {
            Vector<Integer> v = new Vector<>();
            temp = i;
            while (temp > 0) {
                v.add(temp % 10);
                temp /= 10;
            }
//            if (isWonder(v)) {
//                ++count;
//            }
        }
        System.out.println(count);

    }

}
