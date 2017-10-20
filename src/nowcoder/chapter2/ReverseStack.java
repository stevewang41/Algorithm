package nowcoder.chapter2;

import java.util.Stack;

/**
 * Created by wangshiyi on 17/7/26.
 * <p>
 * 只用递归函数实现栈的逆序
 */

public class ReverseStack {


    /**
     * 递归实现栈的逆序
     *
     * @param stack
     */
    public static void reverseStackRecursively(Stack<Integer> stack) {
        if (stack.isEmpty()) {  // 已把栈抽空，开始回溯（反压）
            return;
        }
        int i = getAndRemoveLastElement(stack);
        reverseStackRecursively(stack);
        stack.push(i);          // 把抽出的元素反压入栈
    }

    /**
     * 移除stack的栈底元素，其他元素不变
     *
     * @param stack
     * @return 返回所移除的栈底元素
     */
    public static int getAndRemoveLastElement(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {  // 如果result为栈底元素，将栈底元素回溯
            return result;
        } else {
            int last = getAndRemoveLastElement(stack);  // 拿到栈底元素
            stack.push(result); // push回之前pop的元素
            return last;
        }
    }

    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println("原始栈：" + stack);
        reverseStackRecursively(stack);
        System.out.println("逆序后的栈：" + stack);
    }
}
