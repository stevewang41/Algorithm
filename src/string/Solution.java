package string;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangshiyi on 17/7/24.
 */

public class Solution {


    /**
     * 字符串匹配
     *
     * @param source
     * @param target
     * @return
     */
    public int strStr(String source, String target) {
        if (source == null || source.length() == 0) {
            return -1;
        }
        for (int i = 0; i < source.length() - target.length() + 1; i++) {
            int j;
            for (j = 0; j < target.length(); j++) {
                if (source.charAt(i + j) != target.charAt(j)) {
                    break;
                }
            }
            if (j == target.length()) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 最长无重复字符子串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> latestIndex = new HashMap<>();  // 字符最近一次出现的位置
        int curStart = 0, curLen = 0, maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);               // 添加字符c到上一个已有的最长无重复字符子串的结尾
            if (latestIndex.containsKey(c)) {   // 字符c曾出现过
                curStart = Math.max(curStart, latestIndex.get(c) + 1);  // 更新当前无重复字符子串的起点
            }
            curLen = i - curStart + 1;          // 当前无重复字符子串的长度
            maxLen = Math.max(maxLen, curLen);
            latestIndex.put(c, i);
        }
        return maxLen;
    }


    /**
     * 最长回文子串
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {

        int n = s.length();
        int start = 0, maxLen = 0;
        for (int i = 0; i < n; i++) {   // 以i为中心向两边扩展
            for (int j = 0; i - j >= 0 && i + j < n; j++) { // 长度为奇数的回文串（形如abcba）
                if (s.charAt(i - j) != s.charAt(i + j)) {
                    break;
                }
                if (2 * j + 1 > maxLen) {
                    maxLen = 2 * j + 1;
                    start = i - j;
                }
            }
            for (int j = 0; i - j >= 0 && i + 1 + j < n; j++) { // 长度为偶数的回文串（形如abba），中心点(两个b)位置分别为i和i+1，向两边扩展范围(i-j,i+1+j)
                if (s.charAt(i - j) != s.charAt(i + 1 + j)) {
                    break;
                }
                if (2 * j + 2 > maxLen) {
                    maxLen = 2 * j + 2;
                    start = i - j;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }
}
