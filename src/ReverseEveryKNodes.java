import java.util.Stack;

/**
 * Created by wangshiyi on 17/8/10.
 * <p>
 * 单链表，每k个节点逆序，如果最后一组不够k个节点，不逆序
 */

public class ReverseEveryKNodes {

    static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }


    /**
     * 利用栈，时间复杂度O(N)，空间复杂度O(K)
     *
     * @param head
     * @param k
     * @return
     */
    public static Node reverseKNodesByStack(Node head, int k) {
        if (k < 2) {
            return head;
        }
        Stack<Node> stack = new Stack<>();
        Node newHead = head;    // newHead初始置为head，逆序完第一组就不再为head
        Node cur = head;
        Node next;
        Node lastEnd = null;    // 上一组的最后一个结点
        while (cur != null) {
            next = cur.next;
            stack.push(cur);            // 当前结点入栈
            if (stack.size() == k) {    // 栈大小达到k
                lastEnd = resignByStack(stack, lastEnd, next);
                if (newHead == head) {  // 逆序完第一组，需要保存整个链表的头结点
                    newHead = cur;
                }
            }
            cur = next;
        }
        return newHead;
    }


    /**
     * 将栈中k个结点依次弹出，连接到lastEnd后面，并将最后一个结点指向nextStart
     *
     * @param stack
     * @param lastEnd   上一组的最后一个结点
     * @param nextStart 下一组的第一个结点
     * @return 返回当前组内最后一个结点
     */
    public static Node resignByStack(Stack<Node> stack, Node lastEnd, Node nextStart) {
        Node cur = stack.pop();
        if (lastEnd != null) {     // 非第一组
            lastEnd.next = cur;
        }
        while (!stack.isEmpty()) {
            cur.next = stack.pop();
            cur = cur.next;
        }
        cur.next = nextStart;
        return cur;
    }


    /* ----------------------------- 分割线 ----------------------------- */


    /**
     * 原址逆序，不用栈，时间复杂度O(N)，空间复杂度O(1)
     *
     * @param head
     * @param k
     * @return
     */
    public static Node reverseKNodesAtSource(Node head, int k) {
        if (k < 2) {
            return head;
        }
        Node cur = head;
        Node next;
        Node start;             // 当前组的第一个结点
        Node lastEnd = null;    // 上一组的最后一个结点
        int count = 0;
        while (cur != null) {
            next = cur.next;
            count++;
            if (count == k) {
                if (lastEnd == null) {
                    start = head;   
                    head = cur;   // 第一组，更新头结点
                } else {
                    start = lastEnd.next;
                }
                resignAtSource(lastEnd, start, cur, next);
                lastEnd = start;    // 当前组继续之后，第一个结点变成最后一个结点
                count = 0;          // 计数器复位
            }
            cur = next;
        }
        return head;
    }

    
    /**
     * 将start...end的k个结点依次逆序连接到lastEnd后面，并将最后一个结点start指向nextStart
     *
     * @param lastEnd
     * @param start
     * @param end
     * @param nextStart
     */
    public static void resignAtSource(Node lastEnd, Node start, Node end, Node nextStart) {
        Node pre = start;
        Node cur = start.next;
        Node next;
        while (cur != nextStart) {  // 完成指针逆向
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if (lastEnd != null) {
            lastEnd.next = end;
        }
        start.next = nextStart;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        Node node = reverseKNodesAtSource(head, 2);
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

}
