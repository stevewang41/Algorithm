package job.baidu._3;

/**
 * Created by wangshiyi on 17/9/3.
 * <p>
 * 欧几里德，毕达哥拉斯，帕斯卡和蒙特打算到公园游玩。可视为将公园分为N（行）* M（列）个位置。
 * 帕斯卡、蒙特和欧几里德站在三个不同的位置。毕达哥拉斯是最后一个到达公园的，
 * 他决定站在一个能让四个点形成一个平行四边形的位置。
 * 欧几里德和蒙特的位置则形成平行四边形的对角线。
 * <p>
 * 编写一个算法，帮助毕达哥拉斯决定站在公园的什么位置。
 * <p>
 * 输入
 * 函数/方法的输入包括七个参数
 * rows，表示行数（N）的整数
 * cols，表示列数（M）的整数
 * positionsInPark，表示公园的符号矩阵
 * euclidX，表示欧几里德位置x坐标的整数
 * euclidY，表示欧几里德位置y坐标的整数
 * monteX，表示蒙特位置x坐标的整数
 * monteY，表示蒙特位置y坐标的整数
 * <p>
 * 输出
 * 返回一个整数列表，表示毕达哥拉斯完成平行四边形应站位置的x和y坐标
 * <p>
 * 注意
 * positionsInPark的符号只包含"+"和"-"；其中'+'表示三个朋友之一所站的位置，"-"表示空
 * 位。符号之间没有空格。
 */

public class Solution {

    public int[] toCompleteParallelogram(int rows, int cols,
                                         char[][] positionsInPark, int euclidX,
                                         int euclidY, int monteX, int monteY) {
        int pX = -1;    // 帕斯卡位置x坐标的整数
        int pY = -1;    // 帕斯卡位置y坐标的整数
        int bX;         // 欲求解的毕达哥拉斯位置x坐标的整数
        int bY;         // 欲求解的毕达哥拉斯位置y坐标的整数
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ((i == euclidX && j == euclidY) || (i == monteX && j == monteY)) {
                    continue;
                }
                if (positionsInPark[i][j] == '+') {     // 找到帕斯卡的位置
                    pX = i;
                    pY = j;
                    break;
                }
            }
        }
        bX = euclidX + monteX - pX;     // 利用平行四边形对角线互相平分
        bY = euclidY + monteY - pY;
        int[] res = new int[2];
        res[0] = bX;
        res[1] = bY;
        if (pX == -1 && pY == -1) {
            return null;
        } else {
            return res;
        }
    }
}
