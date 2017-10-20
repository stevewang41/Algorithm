package job;

import java.util.Stack;

/**
 * Created by wangshiyi on 17/9/4.
 * <p>
 * 两个栈实现一个队列，支持基本操作：offer、poll、peek
 */

public class Queue<E> {

    private Stack<E> stackPush;     // 压入栈
    private Stack<E> stackPop;      // 弹出栈

    public Queue() {
        stackPush = new Stack<E>();
        stackPop = new Stack<E>();
    }

    public void offer(E e) {
        stackPush.push(e);
    }

    public E poll() {
        if (stackPop.isEmpty() && stackPush.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        } else if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.pop();
    }

    public E peek() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("Queue is empty");
        } else if (stackPop.empty()) {
            while (!stackPush.empty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.peek();    // 与poll只有此处不同
    }


}
