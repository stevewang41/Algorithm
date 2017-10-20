package douyu.douyu_2017_07_31;

/**
 * Created by wangshiyi on 17/8/1.
 * <p>
 * P336  找到无序数组中最小的K个数（topK问题）
 * <p>
 * 利用最大堆    时间复杂度O(Nlogk)
 * 对于从海量数据(N)中找出TOP K，这种算法仅需一次性将k个数装入内存，其余数据从文件一个一个读即可
 * 所以它是针对海量数据TOP K问题最为有效的算法
 * <p>
 * BFPRT算法    时间复杂度O(N)
 */

public class FindKMinNums {

    /**
     * 维护一个有k个数的最大堆，代表目前选出的最小的k个数
     *
     * @param read 实际场景中，read提供的数据需要从文件中读取，这里为了方便用数组表示
     * @param k
     * @return
     */
    public static int[] getKMinsByHeap(int[] read, int k) {
        if (k < 1 || k > read.length) {
            return read;
        }
        int[] kHeap = new int[k];
        for (int i = 0; i < k; i++) {   // 初始时一次性从文件中读取k个数据
            kHeap[i] = read[i];
        }
        buildHeap(kHeap, k);            // 建堆，时间复杂度O(k)
        for (int i = k; i < read.length; i++) { // 从文件中一个一个的读取剩余数据
            if (read[i] < kHeap[0]) {
                kHeap[0] = read[i];
                heapify(kHeap, 0, k);   // 从堆顶开始向下进行调整，时间复杂度O(logk)
            }
        }
        return kHeap;
    }

    /**
     * 建堆函数
     *
     * @param arr
     * @param n
     */
    public static void buildHeap(int[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, i, n);
        }
    }

    /**
     * 从arr[i]向下进行堆调整
     *
     * @param arr
     * @param i
     * @param heapSize
     */
    public static void heapify(int[] arr, int i, int heapSize) {
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;
        int max = i;
        if (leftChild < heapSize && arr[leftChild] > arr[max]) {
            max = leftChild;
        }
        if (rightChild < heapSize && arr[rightChild] > arr[max]) {
            max = rightChild;
        }
        if (max != i) {
            swap(arr, i, max);
            heapify(arr, max, heapSize);
        }
    }


    /* ----------------------------- 分割线 ----------------------------- */


    /**
     * 先用BFPRT算法求出第k小的数，再遍历一遍数组才能求出最小的k个数，时间复杂度O(N)
     * 需要将所有数据一次性装入内存，适用于非海量数据的情况
     *
     * @param arr
     * @param k
     * @return
     */
    public static int[] getKMins(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return arr;
        }
        int kthMin = getKthMinByBFPRT(arr, k);  // 使用BFPRT算法求得第k小的数，O(N)
        int[] kMins = new int[k];               // 下面遍历一遍数组，利用第k小的数找到最小的k个数，O(N)
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < kthMin) {              // 小于第k小的数，必然属于最小的k个数
                kMins[index++] = arr[i];
            }
        }
        while (index < k) {
            kMins[index++] = kthMin;            // 不足部分用第k小的数补全
        }
        return kMins;
    }

    /**
     * 使用BFPRT算法求第k小的数
     *
     * @param arr
     * @param k
     * @return
     */
    public static int getKthMinByBFPRT(int[] arr, int k) {
        int[] arrCopy = copyArray(arr); // 在得到第k小的数之后还要遍历一遍原数组，所以并不直接操作原数组
        return select(arrCopy, 0, arrCopy.length - 1, k - 1);   // 第k小的数，即排好序后下标为k-1的数
    }

    /**
     * 拷贝数组
     *
     * @param arr
     * @return
     */
    public static int[] copyArray(int[] arr) {
        int[] arrCopy = new int[arr.length];
        for (int i = 0; i < arrCopy.length; i++) {
            arrCopy[i] = arr[i];
        }
        return arrCopy;
    }

    /**
     * 在数组arr的下标范围[begin, end]内，找到排序后位于整个arr数组下标为index的数
     *
     * @param arr
     * @param begin
     * @param end
     * @param index
     * @return
     */
    public static int select(int[] arr, int begin, int end, int index) {
        if (begin == end) {
            return arr[begin];
        }
        int pivot = medianOfMedians(arr, begin, end);   // 核心操作：中位数的中位数作为基准
        int[] pivotRange = partition(arr, begin, end, pivot);   // 拿到分区后中区的范围
        if (index >= pivotRange[0] && index <= pivotRange[1]) { // 命中
            return arr[index];
        } else if (index < pivotRange[0]) {
            return select(arr, begin, pivotRange[0] - 1, index);
        } else {
            return select(arr, pivotRange[1] + 1, end, index);
        }
    }

    /**
     * 选基准
     *
     * @param arr
     * @param begin
     * @param end
     * @return
     */
    public static int medianOfMedians(int[] arr, int begin, int end) {
        int num = end - begin + 1;
        int offset = num % 5 == 0 ? 0 : 1;      // 5个成一组，不满5个的自己成一组
        int[] mArr = new int[num / 5 + offset]; // 每组的中位数取出构成中位数数组mArr
        for (int i = 0; i < mArr.length; i++) {
            int beginI = begin + i * 5;
            int endI = beginI + 4;
            mArr[i] = getMedian(arr, beginI, Math.min(endI, end));
        }
        // 求中位数数组mArr的中位数，作为基准返回
        return select(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    /**
     * 在数组arr的下标范围[begin, end]内，找中位数，如果元素个数为偶数则找下中位数
     *
     * @param arr
     * @param begin
     * @param end
     * @return
     */
    public static int getMedian(int[] arr, int begin, int end) {
        insertionSort(arr, begin, end);
        int sum = begin + end;
        int mid = (sum / 2) + (sum % 2);
        return arr[mid];
    }

    /**
     * 这里仅用于对一组5个数进行插入排序，时间复杂度O(1)
     *
     * @param arr
     * @param begin
     * @param end
     */
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

    /**
     * 优化后的快排partition操作
     *
     * @param arr
     * @param begin
     * @param end
     * @param pivot
     * @return 返回划分后等于基准的元素下标范围
     */
    public static int[] partition(int[] arr, int begin, int end, int pivot) {
        int small = begin - 1;     // 小区最后一个元素下标
        int big = end + 1;         // 大区第一个元素下标
        int cur = begin;
        while (cur < big) {
            if (arr[cur] < pivot) {
                swap(arr, ++small, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, --big, cur);
            } else {
                cur++;
            }
        }
        int[] range = new int[2];
        range[0] = small + 1;      // 中区第一个元素下标
        range[1] = big - 1;        // 中区最后一个元素下标
        return range;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9};
        // sorted : { 1, 1, 1, 1, 2, 2, 2, 3, 3, 5, 5, 5, 6, 6, 6, 7, 9, 9, 9 }
        printArray(getKMinsByHeap(arr, 6));
        printArray(getKMins(arr, 6));
    }
}
