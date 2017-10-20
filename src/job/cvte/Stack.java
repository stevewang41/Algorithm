package job.cvte;

/**
 * Created by wangshiyi on 17/8/11.
 * <p>
 * 实现一个数据结构：栈，考虑扩容，加入线程同步
 */

public class Stack<E> {

    private static final int INIT_SIZE = 2; // 栈的默认初始大小

    private Object[] stack; // Java不支持泛型数组，可使用Java提供的容器，但本题并不允许使用Java提供的容器类

    private int index;      // 栈顶索引


    /**
     * 构造方法，未指定栈的初始大小，使用默认大小
     */
    public Stack() {
        stack = new Object[INIT_SIZE];
        index = -1;
    }

    /**
     * 构造方法，指定栈的初始大小
     *
     * @param initSize
     */
    public Stack(int initSize) {
        if (initSize < 0) {
            throw new IllegalArgumentException();
        }
        stack = new Object[initSize];
        index = -1;
    }

    /**
     * 出栈操作
     *
     * @return 栈顶对象
     */
    public synchronized E pop() {
        if (!isEmpty()) {
            E top = (E) stack[index];
            stack[index--] = null;  // 将要弹出的元素置空，避免内存泄露
            return top;
        }
        return null;
    }

    /**
     * 入栈操作
     *
     * @param obj 等待入栈的对象
     */
    public synchronized void push(E obj) {
        if (isFull()) {
            Object[] src = stack;
            stack = new Object[2 * stack.length];   // 如果栈满，则创建空间为当前栈空间两倍的栈
            System.arraycopy(src, 0, stack, 0, src.length);
        }
        stack[++index] = obj;
    }

    /**
     * 查看栈顶对象
     *
     * @return 栈顶对象
     */
    public E peek() {
        if (!isEmpty()) {
            return (E) stack[index];
        }
        return null;
    }

    /**
     * 查看栈是否为空
     *
     * @return 如果栈为空返回true，否则返回false
     */
    public boolean isEmpty() {
        return index == -1;
    }

    /**
     * 查看栈是否满
     *
     * @return 如果栈满返回true, 否则返回false
     */
    public boolean isFull() {
        return index >= stack.length - 1;
    }

}