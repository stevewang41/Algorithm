package job;

/**
 * Created by wangshiyi on 17/9/5.
 * <p>
 * 传入 int 的123 返回 字符串 “321”（其实是int转化为String 的变种）
 */

public class ReverseInt2String {

    public static String transform(int number) {
        boolean isNegative = false;
        StringBuilder sb = new StringBuilder();
        if (number == 0) {
            return "0";
        } else if (number < 0) {
            isNegative = true;
        }
        number = Math.abs(number);
        if (isNegative) {
            sb.append("-");
        }
        while (number > 0) {
            sb.append(number % 10);
            number /= 10;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(transform(-123));
    }
}
