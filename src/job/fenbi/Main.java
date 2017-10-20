package job.fenbi;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/9/6.
 * <p>
 * 输入1，输出如下
 * 1
 * <p>
 * 输入3，输出如下
 * 1 3 6
 * 2 5
 * 4
 * <p>
 * 请写一个函数，输入为行数，输出如上例子
 */

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int line = sc.nextInt();
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < line - i; j++) {
                System.out.print(i + 2 * j + 1);
                if (j != line - i - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
