package nowcoder.chapter2;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by wangshiyi on 17/7/26.
 *
 * 京东2017校招笔试题
 * 挺难的，笔试那点时间很难100% AC
 * <p>
 * 相邻两座可见
 * 中间的数都不比他俩大（<=）才可见（因为是个环，存在顺时针和逆时针两条通路）
 * 结论：如果环中没有重复元素，结果为 (n - 2) * 2 + 1 = 2 * n - 3
 * <p>
 * 单调栈，由底到顶依次递减（每来一个更大的元素，栈顶出栈），任何一个元素只进栈一次，出栈了就不会再进栈  O(n)
 * 对于一个要出栈的元素，它下边的元素即是它左边离它最近的比它大的元素，使它出栈的元素即是其右边离他最近的比它大的元素
 * <p>
 * 对于有重复元素的情况，从最大值（任选一个，可能不只一个）开始，以一个方向开始按单调栈规则入栈
 */

public class Safeguard {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int size = in.nextInt();
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(communications(arr));
        }
    }

    /**
     * 拿到圆环中下一个元素的索引，因为这里是用数组来表示圆环的
     *
     * @param size
     * @param i
     * @return
     */
    public static int nextIndexInCircle(int size, int i) {
        return i < (size - 1) ? (i + 1) : 0;
    }

    /**
     * 单调栈中在栈顶相遇的相同元素之间构成的可观察岗哨对数
     *
     * @param n
     * @return
     */
    public static long getInternalSum(int n) {
        return n == 1 ? 0L : (long) n * (long) (n - 1) / 2L;
    }


    public static long communications(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int size = arr.length;
        int maxIndex = 0;
        for (int i = 0; i < size; i++) {
            if (arr[maxIndex] < arr[i]) {
                maxIndex = i;
            }
        }
        int value = arr[maxIndex];  // 先找到数组中的一个最大值（可能不止一个）
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(value)); // 先把最大值压入单调栈栈底
        long res = 0L;
        int index = nextIndexInCircle(size, maxIndex);
        while (index != maxIndex) {
            value = arr[index];
            while (!stack.isEmpty() && value > stack.peek().value) {    // 来了一个更大的元素
                int times = stack.pop().times;      // 栈顶元素出栈，并拿到该栈顶元素的累计个数
                // 出栈的栈顶元素之间构成可观察岗哨对数C(times)2 = n*(n-1)/2，当times==1时，构成的可观察岗哨对数为0
                // 出栈的栈顶元素与它下面的元素以及使它出栈的元素所构成的可观察岗哨对数times * 2
                res += getInternalSum(times) + times * 2;
            }
            if (!stack.isEmpty() && value == stack.peek().value) {  // 累加栈顶相遇的相同元素个数
                stack.peek().times++;
            } else {    // stack.isEmpty() || data < stack.peek().data
                stack.push(new Pair(value));
            }
            index = nextIndexInCircle(size, index);
        }
        while (!stack.isEmpty()) {  // 所有的元素都已遍历了一遍，单调栈不空
            int times = stack.pop().times;
            res += getInternalSum(times);   // 相同元素之间构成的可观察岗哨对数
            if (!stack.isEmpty()) {
                res += times;   // 与它下面的元素所构成的可观察岗哨对数   [此处标记]
                if (stack.size() >= 2) {    // 它下面并不是栈底最大值
                    res += times;   // 与栈底最大值所构成的可观察岗哨对数
                } else {    // 它下面已是栈底最大值
                    res += stack.peek().times == 1 ? 0 : times; // 如果它下面的栈底最大值只有1个，显然它已经在有[标记]的那一行加过了
                }
            }
        }
        return res;
    }

    public static class Pair {
        public int value;
        public int times;

        public Pair(int value) {
            this.value = value;
            this.times = 1;
        }
    }
}
