package job.jd;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by wangshiyi on 17/9/8.
 */

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        Stack<Integer> stack = new Stack<>();
        int res = 1;
        for (int j = 0; j < str.length(); j++) {
            if (str.charAt(j) == '(') {
                stack.push(j);
            } else {
                res *= stack.size();
                stack.pop();
            }
        }
        System.out.println(res);
    }
}