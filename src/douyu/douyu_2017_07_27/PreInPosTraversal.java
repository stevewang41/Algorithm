package douyu.douyu_2017_07_27;

import java.util.Stack;

/**
 * Created by wangshiyi on 17/7/29.
 * <p>
 * 递归与非递归实现二叉树的前、中、后序遍历
 * 对于递归实现的三种遍历方式，仅打印当前节点的位置不同，实际上为同一个过程，都经过当前节点3次
 * Morris二叉树遍历其实就是用非递归的方式来高度模拟递归，尽量的多次访问一个节点
 */

public class PreInPosTraversal {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 二叉树先序遍历的递归实现
     *
     * @param head
     */
    public static void preOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " "); // 先打印当前结点
        preOrderRecur(head.left);           // 递归遍历左子树
        preOrderRecur(head.right);          // 递归遍历右子树
    }

    /**
     * 二叉树中序遍历的递归实现
     *
     * @param head
     */
    public static void inOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    /**
     * 二叉树后续遍历的递归实现
     *
     * @param head
     */
    public static void posOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value + " ");
    }

    /**
     * 二叉树先序遍历的非递归实现，利用栈
     *
     * @param head
     */
    public static void preOrderUnRecur(Node head) {
        System.out.print("pre-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(head);    // 先将头结点压栈
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.value + " ");
                if (head.right != null) {
                    stack.push(head.right); // 右孩子先入栈
                }
                if (head.left != null) {
                    stack.push(head.left);  // 左孩子再入栈
                }
            }
        }
        System.out.println();
    }

    /**
     * 二叉树中序遍历的非递归实现，利用栈
     *
     * @param head
     */
    public static void inOrderUnRecur(Node head) {
        System.out.print("in-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);   // 先从头结点开始将左孩子一直压入栈中，直到左孩子为空
                    head = head.left;
                } else {
                    head = stack.pop(); // 当结点从栈中弹出时
                    System.out.print(head.value + " ");
                    head = head.right;  // 跳到它的右孩子，再重复上面的过程（从头结点开始将左孩子一直压入栈中）
                }
            }
        }
        System.out.println();
    }

    /**
     * 二叉树后续遍历的非递归实现，利用一个栈，实现起来比较麻烦
     *
     * @param head
     */
    public static void posOrderUnRecur1(Node head) {
        System.out.print("pos-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            Node c;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && head != c.left && head != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && head != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.print(stack.pop().value + " ");
                    head = c;
                }
            }
        }
        System.out.println();
    }

    /**
     * 二叉树后续遍历的非递归实现，利用两个栈
     * 前面先序遍历实现了 根 -> 左 -> 右 的顺序
     * 修改先序遍历代码很容易实现  根 -> 右 -> 左 的顺序
     * 再用另一个栈实现逆序变为 左 -> 右 -> 根 即可
     *
     * @param head
     */
    public static void posOrderUnRecur2(Node head) {
        System.out.print("pos-order: ");
        if (head != null) {
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            s1.push(head);      // 先将头结点压s1栈
            while (!s1.isEmpty()) {
                head = s1.pop();
                s2.push(head);  // 将原来的从s1栈弹出就打印改为压s2栈，实现逆序
                if (head.left != null) {
                    s1.push(head.left); // 左孩子先入s1栈
                }
                if (head.right != null) {
                    s1.push(head.right);// 右孩子再入s1栈
                }
            }
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().value + " "); // 打印出s2栈中的内容
            }
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        posOrderRecur(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        preOrderUnRecur(head);
        inOrderUnRecur(head);
        posOrderUnRecur2(head);
        posOrderUnRecur1(head);

    }
}
