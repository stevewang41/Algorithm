package job;

/**
 * Created by wangshiyi on 17/9/17.
 */

public class FindMax100 {

    public int[] getMax100ByHeap(int[] read, int k) {

        int[] kHeap = new int[k];
        for (int i = 0; i < k; i++) {
            kHeap[i] = read[i];
        }
        buildHeap(kHeap, k);
        for (int i = k; i < read.length; i++) {
            if (read[i] > kHeap[0]){
                kHeap[0] = read[i];
                heapipy(kHeap, 0, k);
            }
        }
        return kHeap;
    }

    public void buildHeap(int[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapipy(arr, i, n);
        }
    }

    public void heapipy(int[] arr, int i, int heapSize) {
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;
        int min = i;
        if (leftChild < heapSize && arr[leftChild] < arr[min]) {
            min = leftChild;
        }
        if (rightChild < heapSize && arr[rightChild] < arr[min]) {
            min = rightChild;
        }
        if (min != i) {
            swap(arr, min, i);
            heapipy(arr, min, heapSize);
        }
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
