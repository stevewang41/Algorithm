package job.zuoyebang._3;

import java.util.Stack;

/**
 * Created by wangshiyi on 17/9/27.
 * <p>
 * 给定指定二叉树根节点Node root，请给出其非递归的前序遍历
 */

public class Main {

    public static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
        }
    }


    public static void printTree(Node root) {
        System.out.print("pre-order: ");
        if (root != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                root = stack.pop();
                System.out.print(root.data + " ");
                if (root.right != null) {
                    stack.push(root.right);
                }
                if (root.left != null) {
                    stack.push(root.left);
                }
            }
        }
        System.out.println();
    }
}
