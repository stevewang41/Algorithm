package job.fenbi;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by wangshiyi on 17/9/6.
 * <p>
 * 1. 输入一串如下字符串"1+2-34+5"，使用代码解析，并计算出结果。
 */

public class Expression {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String str = in.next();
        Stack<Character> opStack = new Stack<>();

        int res = 0;
        int curNum = 0;
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            if (i == 0) {
                curNum = c - 48;
            } else {
                if (c == '+' || c == '-') {
                    if (!opStack.isEmpty()) {
                        char op = opStack.pop();
                        if (op == '+') {
                            res += curNum;
                        } else {
                            res -= curNum;
                        }
                    } else {
                        res = curNum;
                    }
                    curNum = 0;
                    opStack.push(c);
                } else {
                    curNum = curNum * 10 + c - 48;
                }
            }

        }
        char op = opStack.pop();
        if (op == '+') {
            res += curNum;
        } else {
            res -= curNum;
        }
        System.out.println(res);
    }
}
