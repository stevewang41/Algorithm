package job.didi;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by wangshiyi on 17/8/26.
 */

public class Main {


    public static int getKthMaxByBFPRT(int[] arr, int k) {
        return select(arr, 0, arr.length - 1, k - 1);
    }

    public static int select(int[] arr, int begin, int end, int index) {
        if (begin == end) {
            return arr[begin];
        }
        int pivot = getPivot(arr, begin, end);
        int[] pivotRange = partition(arr, begin, end, pivot);
        if (index >= pivotRange[0] && index <= pivotRange[1]) {
            return arr[index];
        } else if (index < pivotRange[0]) {
            return select(arr, begin, pivotRange[0] - 1, index);
        } else {
            return select(arr, pivotRange[1] + 1, end, index);
        }
    }

    public static int getPivot(int[] arr, int begin, int end) {
        int num = end - begin + 1;
        int offset = num % 5 == 0 ? 0 : 1;
        int[] mArr = new int[num / 5 + offset];
        for (int i = 0; i < mArr.length; i++) {
            int beginI = begin + i * 5;
            int endI = beginI + 4;
            mArr[i] = getMedian(arr, beginI, Math.min(endI, end));
        }
        return select(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    public static int getMedian(int[] arr, int begin, int end) {
        insertionSort(arr, begin, end);
        int sum = begin + end;
        int mid = (sum / 2) + (sum % 2);
        return arr[mid];
    }

    public static void insertionSort(int[] arr, int begin, int end) {
        for (int i = begin + 1; i <= end; i++) {
            int get = arr[i];
            int j = i - 1;
            while (j >= begin && arr[j] > get) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = get;
        }
    }

    public static int[] partition(int[] arr, int begin, int end, int pivot) {
        int small = begin - 1;
        int big = end + 1;
        int cur = begin;
        while (cur < big) {
            if (arr[cur] > pivot) {
                swap(arr, ++small, cur++);
            } else if (arr[cur] < pivot) {
                swap(arr, --big, cur);
            } else {
                cur++;
            }
        }
        int[] range = new int[2];
        range[0] = small + 1;
        range[1] = big - 1;
        return range;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        while (in.hasNextInt()) {
            list.add(in.nextInt());
        }
        int[] arr = new int[list.size() - 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        System.out.println(getKthMaxByBFPRT(arr, list.get(arr.length)));
    }
}