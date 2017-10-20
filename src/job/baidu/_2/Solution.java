package job.baidu._2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wangshiyi on 17/9/2.
 * <p>
 * 在一个家庭中，每位成员都有手机。这家户主维护一棵家族树，树的没每个节点代表一位家庭
 * 成员，每个节点的值代表他/她所拥有的手机数量。户主作为这棵树的根。户主想要找到同
 * 一代家族成员所拥有手机的最大数量。属于树中同一级的所有成员都被视为同一代。
 * <p>
 * 编写一个算法，求出同一代家庭成员所拥有手机的最大数量
 * <p>
 * 输入
 * 函数/方法的输入包含一个参数 - familyRoot，表示家族树的根
 * <p>
 * 输出
 * 返回表示同一代家庭成员所拥有手机的最大数量的整数
 * <p>
 * 限制条件
 * 0 <= N <= 10^5；其中N表示节点数
 * 0 <= M <= 10^5；其中M表示同一代人所拥有的手机数
 * <p>
 * 请注意
 * 每个节点最多有100个子节点
 * <p>
 * 输入：
 * 10
 * /   \
 * /    \
 * 11    10
 * / \   / \
 * 2  3  1  5
 * <p>
 * 输出：
 * 21
 * <p>
 * 说明：
 * 第 1 步：第1、2和3级的总和分别是10、21、和11
 * 第 2 步：第2级的最大总和是21
 * 因此输出为 21
 */

public class Solution {

    private static HashMap<Integer, Integer> levelToSum = new HashMap<>();


    private static int maxLevelSum(NAryNode familyRoot) {

        dfsRecursive(familyRoot, 0);
        int maxSum = Integer.MIN_VALUE;
        for (int l = 0; l < levelToSum.size(); l++) {
            int sum = levelToSum.get(l);
            if (sum > maxSum) {
                maxSum = sum;
            }
        }
        return maxSum;
    }

    /**
     * DFS递归，好处是不用记录每个结点的level
     *
     * @param node
     * @param level
     */
    private static void dfsRecursive(NAryNode node, int level) {
        int sum;
        if (levelToSum.containsKey(level)) {
            sum = levelToSum.get(level) + node.key;
        } else {
            sum = node.key;
        }
        levelToSum.put(level, sum);
        for (NAryNode child : node.child) {
            dfsRecursive(child, level + 1);
        }
    }

    public static void main(String[] args) {
        NAryNode familyRoot = new NAryNode(10);
        NAryNode firstLevel1 = new NAryNode(11);
        NAryNode firstLevel2 = new NAryNode(10);
        NAryNode secondLevel1 = new NAryNode(2);
        NAryNode secondLevel2 = new NAryNode(3);
        NAryNode secondLevel3 = new NAryNode(1);
        NAryNode secondLevel4 = new NAryNode(5);

        familyRoot.child.add(firstLevel1);
        familyRoot.child.add(firstLevel2);
        firstLevel1.child.add(secondLevel1);
        firstLevel1.child.add(secondLevel2);
        firstLevel2.child.add(secondLevel3);
        firstLevel2.child.add(secondLevel4);

        System.out.println(maxLevelSum(familyRoot));
    }
}

class NAryNode {
    int key;
    List<NAryNode> child;

    public NAryNode(int key) {
        this.key = key;
        this.child = new ArrayList<>();
    }
}
