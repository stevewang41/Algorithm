package job;

/**
 * Created by wangshiyi on 17/9/10.
 * <p>
 * N！末尾有多少个0
 *
 * https://leetcode.com/problems/factorial-trailing-zeroes/description/
 */

public class FactorialTrailingZeroes {

    public int trailingZeroes(int n) {
        int zeros = 0;
        while (n > 0) {
            n /= 5;
            zeros += n;
        }
        return zeros;
    }
}
