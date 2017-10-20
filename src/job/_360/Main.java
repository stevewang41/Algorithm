package job._360;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by wangshiyi on 17/8/26.
 */

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        List<Integer> list = countOfSmallerNumberII(a);
        for (int i = 0; i < list.size() - 1; i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println(list.get(list.size() - 1));
    }

    static class Node {
        int begin;
        int end;
        int mid;
        int count;
        Node left;
        Node right;

        Node(int begin, int end) {
            this.begin = begin;
            this.end = end;
            mid = (begin + end) / 2;
            count = 0;
            left = null;
            right = null;
        }

        int add(int num) {
            ++count;
            if (begin == end) {
                return 0;
            } else {
                if (left == null) {
                    left = new Node(begin, mid);
                }
                if (right == null) {
                    right = new Node(mid + 1, end);
                }

                if (num <= mid) {
                    return left.add(num);
                } else {
                    return left.count + right.add(num);
                }
            }
        }

    }

    private static ArrayList<Integer> countOfSmallerNumberII(int[] A) {
        // write your code here
        ArrayList<Integer> r = new ArrayList<>();
        if (A.length == 0) {
            return r;
        }

        int min = A[0];
        int max = A[0];
        for (int i = 0; i < A.length; ++i) {//找出最大和最小值
            if (A[i] < min) {
                min = A[i];
            }
            if (A[i] > max) {
                max = A[i];
            }
        }
        Node node = new Node(min, max); //一边构建线段树一边计算答案
        for (int i = 0; i < A.length; ++i) {
            r.add(node.add(A[i]));
        }
        return r;
    }
}
