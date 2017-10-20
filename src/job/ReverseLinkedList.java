package job;

/**
 * Created by wangshiyi on 17/9/4.
 * <p>
 * 单链表就地逆转
 */

public class ReverseLinkedList {

    static Node head;
    static Node p;
    static Node prev;
    static Node latter;


    public static void main(String[] args) {

        head = new Node(0);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(4);

        prev = null;
        p = head;
        while (p != null) {
            latter = p.next;
            p.next = prev;
            prev = p;
            p = latter;
        }
        head = prev;

        System.out.println(head.value);
        System.out.println(head.next.value);
        System.out.println(head.next.next.value);
        System.out.println(head.next.next.next.value);
        System.out.println(head.next.next.next.next.value);
    }
}

class Node {

    Node next;
    int value;

    Node(int value) {
        this.value = value;
    }
}
