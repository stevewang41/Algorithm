package job.lianjia;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/8/19.
 */

public class Main {

    private static int swapCount = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }
        in.close();
        InsertionSort(b, n);
        System.out.println(swapCount);
    }

    public static void InsertionSort(int A[], int n) {
        for (int i = 1; i < n; i++) {
            int get = A[i];                 // 右手抓到一张扑克牌
            int j = i - 1;                  // 拿在左手上的牌总是排序好的
            while (j >= 0 && A[j] > get) {
                A[j + 1] = A[j];            // 如果该手牌比抓到的牌大，就将其右移
                j--;
            }
            if (j != i - 1) {
                swapCount++;
            }
            A[j + 1] = get; // 直到该手牌比抓到的牌小(或二者相等)，将抓到的牌插入到该手牌右边(相等元素的相对次序未变，所以插入排序是稳定的)
        }
    }
}
