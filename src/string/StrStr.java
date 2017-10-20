package string;

/**
 * Created by wangshiyi on 17/7/24.
 * <p>
 * 字符串匹配
 */

public class StrStr {

    public static void main(String[] args) {
        String source = "abcdabcdefg";
        String target = "bcd";
        System.out.println(new Solution().strStr(source, target));
    }
}