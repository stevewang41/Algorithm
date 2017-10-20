import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * Created by wangshiyi on 17/6/10.
 * <p>
 * http://www.jianshu.com/p/b656e3642545
 */

public class BinaryTreePathSum {

    public static void main(String[] args) {
        //   构造测试集
        //       1
        //      / \
        //     2   4
        //    / \
        //   2   3
        TreeNode leaf2 = new TreeNode(2);
        TreeNode leaf3 = new TreeNode(3);
        TreeNode leaf4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2, leaf2, leaf3);
        TreeNode root1 = new TreeNode(1, node2, leaf4);

        System.out.println(binaryTreePathSum(root1, 5));
    }

    public static List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        Stack<Integer> path = new Stack<>();
        binaryTreePathSum(result, path, root, 0, target);
        return result;
    }

    public static void binaryTreePathSum(List<List<Integer>> result, Stack<Integer> path,
                                         TreeNode root, int sum, int target) {
        sum += root.val;
        path.push(root.val);
        if (sum == target && null == root.left && null == root.right) {
            List<Integer> list = new ArrayList<>(path); // 重新new一个ArrayList，深度克隆path
            result.add(list);
            path.pop();
            return;
        }
        if (root.left != null) {
            binaryTreePathSum(result, path, root.left, sum, target);
        }
        if (root.right != null) {
            binaryTreePathSum(result, path, root.right, sum, target);
        }
        path.pop();
    }
}

class TreeNode {

    int val;
    TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


