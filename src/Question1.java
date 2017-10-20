import java.util.Scanner;

/**
 * Created by wangshiyi on 17/7/25.
 */

public class Question1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int temp;
        long result = 1;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                temp = 2;
                for (int j = 2; Math.pow(i, j) <= n; j++) {
                    temp++;
                }
                result = result * temp % 1000000007;
            }
        }
        System.out.println(result);
    }

    public static boolean isPrime(int n) {
        int i;
        for (i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
