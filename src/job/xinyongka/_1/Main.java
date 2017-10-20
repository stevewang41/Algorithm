package job.xinyongka._1;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/9/13.
 */

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int a = in.nextInt();
            int b = in.nextInt();
            int n = in.nextInt();
            int lcm = leastCommonMult(a, b);
            int sum = lcm;
            int res = 0;
            while (sum <= n){
                res++;
                sum+= lcm;
            }
            System.out.println(res);
        }
    }


    public static int leastCommonMult(int a, int b) {
        int c;
        for (c = a; ; c++) {
            if (c % a == 0 && c % b == 0) {
                break;
            }
        }
        return c;
    }
}
