package douyu.douyu_2017_07_31;

/**
 * Created by wangshiyi on 17/8/1.
 * <p>
 * P491 字符串匹配问题
 * <p>
 * 最优解 KMP算法 时间复杂度O(N + M)
 */

    public class KMPAlgorithm {

        /**
         * 由target构造next数组，时间复杂度O(M)
         *
         * @param target
         * @return
         */
        public static int[] getNextArray(String target) {
            int len = target.length();
            if (len == 1) {
                return new int[]{-1};
            }
            int[] next = new int[len];
            next[0] = -1;
            next[1] = 0;
            int i = 2;
            int cn = 0;     // 向前跳的位置，初始时置0
            while (i < len) {
                if (target.charAt(i - 1) == target.charAt(cn)) {
                    next[i++] = ++cn;
                } else if (cn > 0) {    // 不等，继续往前跳
                    cn = next[cn];
                } else {
                    next[i++] = 0;
                }
            }
            return next;
        }

        /**
         * KMP的匹配过程，source指针不回退，target沿着source向右滑动，时间复杂度O(N)
         *
         * @param source
         * @param target
         * @return
         */
        public static int getIndexOf(String source, String target) {
            if (source == null || target == null || target.length() < 1 || source.length() < target.length()) {
                return -1;
            }
            int n = source.length();
            int m = target.length();
            int i = 0;
            int j = 0;
            int[] next = getNextArray(target);
            while (i < n && j < m) {
                if (source.charAt(i) == target.charAt(j)) {
                    i++;
                    j++;
                } else if (next[j] == -1) {
                    i++;
                } else {
                    j = next[j];
                }
            }
            return j == m ? i - j : -1;
        }


        public static void main(String[] args) {
            String str = "abcabcababaccc";
            String match = "ababa";
            System.out.println(getIndexOf(str, match));

        }
    }
