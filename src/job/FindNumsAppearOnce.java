package job;

/**
 * Created by wangshiyi on 17/10/14.
 * <p>
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 * <p>
 * https://www.nowcoder.com/questionTerminal/e02fdb54d7524710a7d664d082bb7811
 */

public class FindNumsAppearOnce {

    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        if (array == null || array.length < 2) {
            return;
        }
        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            temp ^= array[i];
        }
        int indexOf1 = findFirstBitIs(temp);
        for (int i = 0; i < array.length; i++) {
            if (isBit(array[i], indexOf1)) {
                num1[0] ^= array[i];
            } else {
                num2[0] ^= array[i];
            }
        }
    }

    /**
     * 找到num中第一个为1的位
     *
     * @param num
     * @return
     */
    public int findFirstBitIs(int num) {
        int indexBit = 0;
        while (((num & 1) == 0) && (indexBit) < 8 * 4) {
            num = num >> 1;
            ++indexBit;
        }
        return indexBit;
    }

    /**
     * 判断num的第IndexBit位是否为1，为1返回true
     *
     * @param num
     * @param indexBit
     * @return
     */
    public boolean isBit(int num, int indexBit) {
        num = num >> indexBit;
        return (num & 1) == 1;
    }

}
