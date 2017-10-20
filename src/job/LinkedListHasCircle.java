package job;

/**
 * Created by wangshiyi on 17/9/4.
 * <p>
 * 判断一个单链表是否有环
 * 快指针每次前进3步，慢指针每次前进1步是否可以？
 * 可以，只要快指针每次前进的步数 2 =< k != 环长+1，快慢指针就一定会相遇
 * 取 k = 2，可以保证两圈内快慢指针相遇
 */

public class LinkedListHasCircle {

    boolean hasCircle(Node head) {
        Node pFast = head;
        Node pSlow = head;
        while (pFast != null && pFast.next != null) {
            pFast = pFast.next.next;    // 快指针每次前进2步
            pSlow = pSlow.next;         // 慢指针每次前进1步
            if (pFast == pSlow) {       // 相遇，存在环
                return true;
            }
        }
        return false;
    }
}