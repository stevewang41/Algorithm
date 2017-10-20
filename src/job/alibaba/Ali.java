package job.alibaba;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by wangshiyi on 17/3/5.
 */

public class Ali {


    static String decrypt(String s) {

        try {
            String str = new String(s.getBytes(), "utf-8");
            Integer num = ~Integer.valueOf(str);
            long rev = Long.reverseBytes(num);
            ByteBuffer buffer = ByteBuffer.allocate(str.length());
            buffer.putLong(0, rev);
            byte[] bArr = buffer.array();
            Random r = new Random();
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] -= r.nextInt(100);
            }
            return bArr.toString();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String res;

        String _s;
        try {
            _s = in.nextLine();
        } catch (Exception e) {
            _s = null;
        }

        res = decrypt(_s);
        System.out.println(res);
    }

}
