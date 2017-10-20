package nowcoder.chapter3;

import java.util.Scanner;

/**
 * 原题为统计岛的个数，与数房子为同一题
 * http://www.jianshu.com/p/911a1281ce6e
 */
public class CountHourse {

    public static int countHourse(int[][] matrix) {
        if (matrix == null || matrix[0] == null) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) {
                    count++;               // 发现屋顶，房子数增加
                    infect(matrix, i, j);  // 以发现的屋顶为起点向四个方向进行DFS递归感染
                }
            }
        }
        return count;
    }

    /**
     * 以matrix[i][j]为起点向四个方向进行DFS递归感染
     *
     * @param i
     * @param j
     * @param matrix
     */
    public static void infect(int[][] matrix, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
            return;
        }
        if (matrix[i][j] == 1) {
            matrix[i][j] = 0;           // 感染当前结点
            infect(matrix, i - 1, j);   // 向上方递归感染
            infect(matrix, i + 1, j);   // 向下方递归感染
            infect(matrix, i, j - 1);   // 向左方递归感染
            infect(matrix, i, j + 1);   // 向右方递归感染
        }
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int row = scan.nextInt();
            int col = scan.nextInt();
            int[][] matrix = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    matrix[i][j] = scan.nextInt();
                }
            }
            System.out.println(countHourse(matrix));
        }
        scan.close();
    }
}