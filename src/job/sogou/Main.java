package job.sogou;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by wangshiyi on 17/9/8.
 */

public class Main {

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.valueOf(reader.readLine());
            double max = 0;
            double min = 360;
            for (int i = 0; i < n; i++) {
                double tmp = Double.valueOf(reader.readLine());
                if (i == 0) {
                    min = tmp;
                }
                double maxTmp;
                if (tmp - min > 180) {
                    maxTmp = 360 + min - tmp;
                } else {
                    maxTmp = tmp - min;
                }
                if (maxTmp > max) {
                    max = maxTmp;
                }
                if (max == 180) {
                    break;
                }
            }
            System.out.println(String.format("%.8f", max));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}