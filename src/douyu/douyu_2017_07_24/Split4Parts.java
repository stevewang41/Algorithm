package douyu.douyu_2017_07_24;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by wangshiyi on 17/7/29.
 *
 * 阿里巴巴2017实习生春招编程测验 —— 数组四等分
 * 已知一个全是正数的数组，返回是否能将该数组分割成4部分，每一部分累加和相等，分割点不算
 */

public class Split4Parts {

    /**
     * 第一种方法，构建HashSet，用来查询中间的分割点是否存在
     *
     * @param arr
     * @return
     */
    public static boolean canSplits1(int[] arr) {
        if (arr == null || arr.length < 7) {    // 元素个数小于7，无法构成3个分割点、4个切片的基本结构
            return false;
        }
        HashSet<String> set = new HashSet<>();
        int allSum = 0;         // 所有元素的和
        for (int i = 0; i < arr.length; i++) {
            allSum += arr[i];
        }
        int m2LeftSum = arr[0]; // 分割点m2移动到某个元素时，该元素左边部分（不包括该元素）的累加和
        int m2RightSum;         // 分割点m2移动到某个元素时，该元素右边部分（不包括该元素）的累加和
        for (int i = 1; i < arr.length - 1; i++) {
            m2RightSum = allSum - m2LeftSum - arr[i];
            set.add(m2LeftSum + "_" + m2RightSum);  // HashSet进行增删改查的时间复杂度都是O(1)
            m2LeftSum += arr[i];
        }
        int m1 = 1;
        int m3 = arr.length - 2;
        int m1LeftSum = arr[0];     // 分割点m1左边部分的累加和，不包括自己
        int m3RightSum = arr[arr.length - 1]; // 分割点m3右边部分的累加和，不包括自己
        while (m1 < m3 - 3) {       // 分割点m1和分割点m3中间至少存在3个元素
            if (m1LeftSum == m3RightSum) {     // 这时才有可能存在分割点m2使数组4等分
                int part = m1LeftSum;
                String m2LeftSumStr = String.valueOf(part + arr[m1] + part);
                String m2RightSumStr = String.valueOf(part + arr[m3] + part);
                if (set.contains(m2LeftSumStr + "_" + m2RightSumStr)) {  // 查找分割点m2
                    return true;
                }
                m1LeftSum += arr[m1++];   // 未找到分割点m2，继续移动m1、m3
            } else if (m1LeftSum < m3RightSum) {
                m1LeftSum += arr[m1++];
            } else {
                m3RightSum += arr[m3--];
            }
        }
        return false;
    }


    /**
     * 第二种方法，仅将分割点m1由左向右移动
     * 如果数组可以四等分，依据3个分割点，4个切片这样一个基本结构
     * 一旦分割点m1确定了，m2、m3也就相应确定了
     * 如果最后找不到符合基本结构的m2、m3，则可以断定数组不可以四等分
     *
     * @param arr
     * @return
     */
    public static boolean canSplits2(int[] arr) {
        if (arr == null || arr.length < 7) {    // 元素个数小于7，无法构成3个分割点、4个切片的基本结构
            return false;
        }
        HashMap<Long, Integer> leftSumToIndex = new HashMap<>();
        Long sum = (long) arr[0];
        for (int i = 1; i < arr.length; i++) {
            leftSumToIndex.put(sum, i);    // 把(arr[i]左边所有元素的和, i)存入HashMap
            sum += arr[i];
        }
        Long part = (long) arr[0];         // part为切片内元素的和
        for (int m1 = 1; m1 < arr.length - 5; m1++) {   // 遍历分割点m1
            Long m2LeftSum = part + arr[m1] + part;     // 分割点m2左边所有元素的和
            if (leftSumToIndex.containsKey(m2LeftSum)) {
                int m2 = leftSumToIndex.get(m2LeftSum);
                Long m3LeftSum = m2LeftSum + arr[m2] + part;  // 分割点m3左边所有元素的和
                if (leftSumToIndex.containsKey(m3LeftSum)) {
                    int m3 = leftSumToIndex.get(m3LeftSum);
                    if (m3LeftSum + arr[m3] + part == sum) {
                        return true;
                    }
                }
            }
            part += arr[m1];
        }
        return false;
    }

    /**
     * 生成一个随机数组
     *
     * @return
     */
    public static int[] generateRondomArray() {
        int[] res = new int[(int) (Math.random() * 10) + 7];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * 10) + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int testTimes = 3000000;
        boolean hasErr = false;
        for (int i = 0; i < testTimes; i++) {           // 300W个测试用例
            int[] arr = generateRondomArray();
            if (canSplits1(arr) ^ canSplits2(arr)) {    // 两个算法都去跑，结果不一样就有错误
                hasErr = true;
                break;
            }
        }
        if (hasErr) {
            System.out.println("233333");
        } else {
            System.out.println("666666");
        }

    }

}
