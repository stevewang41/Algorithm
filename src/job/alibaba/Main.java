package job.alibaba;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/8/25.
 */

public class Main {

    public static int countStar(char[][] matrix) {
        int n = matrix.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ('*' == matrix[i][j]) {
                    count++;
                    infect(matrix, i, j);
                }
            }
        }
        return count;
    }


    public static void infect(char[][] matrix, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
            return;
        }
        if ('*' == matrix[i][j]) {
            matrix[i][j] = '0';
            infect(matrix, i - 1, j);
            infect(matrix, i + 1, j);
            infect(matrix, i, j - 1);
            infect(matrix, i, j + 1);
            infect(matrix, i - 1, j - 1);
            infect(matrix, i + 1, j - 1);
            infect(matrix, i - 1, j + 1);
            infect(matrix, i + 1, j + 1);
        }
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        char[][] matrix = new char[n][n];
        for (int i = 0; i < n; i++) {
            String buf = scan.next();
            for (int j = 0; j < n; j++) {
                matrix[i][j] = buf.charAt(j);
            }
        }
        System.out.println(countStar(matrix));
        scan.close();
    }
}
