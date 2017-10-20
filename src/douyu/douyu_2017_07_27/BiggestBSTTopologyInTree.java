package douyu.douyu_2017_07_27;

import com.example.algorithm.BinaryTreeUtils;

import java.util.HashMap;
import java.util.Map;
import static com.example.algorithm.BinaryTreeUtils.Node;

/**
 * Created by wangshiyi on 17/7/29.
 *
 * 给定一棵二叉树的头节点head，已知所有节点的值都不一样，
 * 返回其中最大的且符合搜索二叉树条件的最大拓扑结构的大小
 */

public class BiggestBSTTopologyInTree {


    /**
     * 暴力求解
     * 遍历所有的二叉树节点，并在以每个节点为头的子树中都求一遍符合搜索二叉树条件
     * （按二分搜索的方式在二叉树中查找每个子节点，如果找得到，就说明这个子节点符合搜索二叉树条件）的最大拓扑结构
     *
     * @param head
     * @return
     */
    public static int bstTopoSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int max = maxTopo(head, head);
        max = Math.max(bstTopoSize1(head.left), max);
        max = Math.max(bstTopoSize1(head.right), max);
        return max;
    }

    public static int maxTopo(Node h, Node n) {
        if (h != null && n != null && isBSTNode(h, n, n.value)) {
            return maxTopo(h, n.left) + maxTopo(h, n.right) + 1;
        }
        return 0;
    }

    public static boolean isBSTNode(Node h, Node n, int value) {
        if (h == null) {
            return false;
        }
        if (h == n) {
            return true;
        }
        return isBSTNode(h.value > value ? h.left : h.right, n, value);
    }




    public static class Record {
        public int l;
        public int r;

        public Record(int left, int right) {
            this.l = left;
            this.r = right;
        }
    }

    public static int bstTopoSize2(Node head) {
        Map<Node, Record> map = new HashMap<Node, Record>();
        return posOrder(head, map);
    }

    public static int posOrder(Node h, Map<Node, Record> map) {
        if (h == null) {
            return 0;
        }
        int ls = posOrder(h.left, map);
        int rs = posOrder(h.right, map);
        modifyMap(h.left, h.value, map, true);
        modifyMap(h.right, h.value, map, false);
        Record lr = map.get(h.left);
        Record rr = map.get(h.right);
        int lbst = lr == null ? 0 : lr.l + lr.r + 1;
        int rbst = rr == null ? 0 : rr.l + rr.r + 1;
        map.put(h, new Record(lbst, rbst));
        return Math.max(lbst + rbst + 1, Math.max(ls, rs));
    }

    public static int modifyMap(Node n, int v, Map<Node, Record> m, boolean s) {
        if (n == null || (!m.containsKey(n))) {
            return 0;
        }
        Record r = m.get(n);
        if ((s && n.value > v) || ((!s) && n.value < v)) {
            m.remove(n);
            return r.l + r.r + 1;
        } else {
            int minus = modifyMap(s ? n.right : n.left, v, m, s);
            if (s) {
                r.r = r.r - minus;
            } else {
                r.l = r.l - minus;
            }
            m.put(n, r);
            return minus;
        }
    }
    
    public static void main(String[] args) {
        Node head = new Node(6);
        head.left = new Node(1);
        head.left.left = new Node(0);
        head.left.right = new Node(3);
        head.right = new Node(12);
        head.right.left = new Node(10);
        head.right.left.left = new Node(4);
        head.right.left.left.left = new Node(2);
        head.right.left.left.right = new Node(5);
        head.right.left.right = new Node(14);
        head.right.left.right.left = new Node(11);
        head.right.left.right.right = new Node(15);
        head.right.right = new Node(13);
        head.right.right.left = new Node(20);
        head.right.right.right = new Node(16);
        BinaryTreeUtils.printTree(head);

        System.out.println(bstTopoSize1(head));
        System.out.println(bstTopoSize2(head));

    }
}
