package job;

/**
 * Created by wangshiyi on 17/8/31.
 *
 * 实现一个数据结构：栈，支持泛型，考虑扩容，加入线程同步
 */

public class Stack<E> {

    private static final int INIT_SIZE = 2;
    private Object[] stack;
    private int index;


    public Stack() {
        stack = new Object[INIT_SIZE];
        index = -1;
    }


    public Stack(int initSize) {
        if (initSize < 0) {
            throw new IllegalArgumentException();
        }
        stack = new Object[initSize];
        index = -1;
    }


    public synchronized E pop() {
        if (!isEmpty()) {
            E top = (E) stack[index];
            stack[index--] = null;
            return top;
        }
        return null;
    }


    public synchronized void push(E element) {
        if (isFull()) {
            Object[] src = stack;
            stack = new Object[2 * src.length];
            System.arraycopy(src, 0, stack, 0, src.length);
        }
        stack[++index] = element;
    }

    public boolean isEmpty() {
        return index == -1;
    }

    public boolean isFull() {
        return index >= stack.length - 1;
    }

}
