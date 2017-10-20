package job.baidu._1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by wangshiyi on 17/9/2.
 *
 * Peter正在给围栏重新刷油漆，围栏由N块木板组成。他的桶里有K种颜料，编号从1到
 * K。最初围栏是棕色，用数字0表示。重新刷油漆分为M个步骤。在每一步中，Peter选择数
 * 字L和R，且L <= R，并用K种颜料其中之一完全重涂从第L个到第R块（包括两者）木板。
 * Peter不想让围栏太单调，所以在每一步之后，他想要知道用相同颜料所涂最多木板数（木
 * 板不必连续）
 * 编写一个算法，求出每步之后相同颜色最多木板数
 *
 * 输入
 * 函数/方法的输入包含四个参数 -
 * numOfPlanks，表示木板数的整数（N）
 * numOfColors，表示颜色数的整数（K）
 * numOfStep，表示步骤数的整数（M）
 * steps，整数列表，该列表的每个元素是一个三元组，分别表示左木板（L）、右木板（R）和要涂的颜料（C）
 *
 * 输出
 * 返回一个由M个整数表示的列表，表示每步之后用相同颜料油漆的最多木板数。
 *
 * 限制条件
 * 1 <= numOfPlanks，numOfColors，numOfSteps <= 10^5
 * 1 <= steps[i][0] <= steps[i][1] <= numOfPlanks
 * 1 <= steps[i][2] <= numOfColors
 * 0 <= i <= numOfSteps
 *
 * 示例
 * 输入：
 * numOfPlanks = 5
 * numOfColors = 5
 * numOfSteps = 4
 * steps =
 * [[2, 3, 5],
 *  [4, 5, 2],
 *  [4, 5, 1],
 *  [1, 5, 4]]
 *
 * 输出：
 * 3 2 2 5
 *
 * 说明：
 * 第 1 步：木板 2 和木板 3 的颜色变为 5，相同颜色的最大数量重复 3 次
 * 第 2 步：木板 4 和木板 5 的颜色变为 2，相同颜色的最大数量重复 2 次
 * 第 3 步：木板 4 和木板 5 的颜色变为 1，相同颜色的最大数量重复 2 次
 * 第 4 步：木板 1 和木板 5 的颜色变为 4，相同颜色的最大数量重复 5 次
 * 因此输出列表为 3 2 2 5
 *
 *
 * 一种简单的线段树做法
 * https://www.nowcoder.com/discuss/37141
 *
 */

public class Solution {

    public static ArrayList<Integer> fence(int numOfPlanks, int numOfColors, int numOfSteps, int[][] steps) {

        ArrayList<Integer> maxColorCounts = new ArrayList<>();
        int[] plank = new int[numOfPlanks + 1];
        for (int i = 0; i < numOfSteps; i++) {
            for (int j = steps[i][0]; j <= steps[i][1]; j++) {
                plank[j] = steps[i][2];
            }
            maxColorCounts.add(getMaxColorCount(plank));
        }
        return maxColorCounts;
    }

    public static int getMaxColorCount(int[] array) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                map.put(array[i], map.get(array[i]) + 1);
            } else {
                map.put(array[i], 1);
            }
        }
        Collection<Integer> count = map.values();
        return Collections.max(count);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfPlanks = 5;
        int numOfColors = 5;
        int numOfStep = 4;
        int[][] steps = new int[numOfStep][3];
        /* 2 3 5
           4 5 2
           4 5 1
           1 5 4 */
        for (int i = 0; i < numOfStep; i++) {
            for (int j = 0; j < 3; j++) {
                steps[i][j] = scanner.nextInt();
            }
        }
        System.out.println(fence(numOfPlanks, numOfColors, numOfStep, steps));
    }

}


