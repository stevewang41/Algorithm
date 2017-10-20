package job.lianjia;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by wangshiyi on 17/8/19.
 */

public class AVG {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int r = in.nextInt();
        int avg = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        int total = 0;
        HashSet<Integer> indexSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            total += a[i];
            b[i] = in.nextInt();
            if (a[i] < r) {
                indexSet.add(i);
            }
        }
        in.close();
        int need = n * avg - total;
        int bSum = 0;
        for (int i = 0; i < need; i++) {
            int minIndex = getMinBIndex(r, a, b, indexSet);
            bSum += b[minIndex];
        }
        System.out.println(bSum);
    }

    private static int getMinBIndex(int r, int[] a, int[] b, HashSet<Integer> indexSet) {
        Iterator<Integer> iter = indexSet.iterator();
        int minB = Integer.MAX_VALUE;
        int minIndex = -1;
        while (iter.hasNext()) {
            int i = iter.next();
            if (b[i] < minB) {
                minB = b[i];
                minIndex = i;
            }
        }
        if (++a[minIndex] >= r) {
            indexSet.remove(minIndex);
        }
        return minIndex;
    }
}
