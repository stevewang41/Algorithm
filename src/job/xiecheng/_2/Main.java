package job.xiecheng._2;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/9/21.
 */

public class Main {

    public static String SEED = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz+/";

    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static String encode(byte[] data) {
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int more_len = len % 3;
        int use_len = len - more_len;
        byte[] bytes = new byte[4];
        for (int i = 0; i < use_len; i += 3) {
//            bytes[0] = SEED.charAt(firstByte(data[i]));
//            bytes[1] = SEED[secondByte(data[i], data[i + 1])];
//            bytes[2] = SEED[thirdByte(data[i + 1], b[i + 2])];
//            bytes[3] = SEED[fourthByte(data[i + 2])];
            sb.append(new String(bytes));
        }
        if (more_len == 1) {
            byte b_2[] = new byte[2];
//            b_2[0] = SEED[firstByte(data[len - 1])];
//            b_2[1] = SEED[lastOneByte(data[len - 1], 6)];
            sb.append(new String(b_2));
            return sb.append("==").toString();
        } else if (more_len == 2) {
            byte b_3[] = new byte[3];
//            b_3[0] = SEED[firstByte(data[len - 2])];
//            b_3[1] = SEED[secondByte(data[len - 2], data[len - 1])];
//            b_3[2] = SEED[lastOneByte(data[len - 1], 4)];
            sb.append(new String(b_3));
            return sb.append("=").toString();
        }
        return sb.toString();
    }


    public static byte firstByte(byte b) {
        //对字节b右移2位
        int r_f = b & 0xff;
        r_f = r_f >>> 2;
        return (byte) (r_f & 0x3f);
    }


    public static byte secondByte(byte last_b, byte next_b) {
        //取last_b的低2位和next_b的高4位
        int r_l = last_b & 0xff;
        int r_n = next_b & 0xff;
        r_l = last_b & 0x03;//last_b去掉高6位
        r_l = r_l << 4;//last_b左移4位
        r_n = r_n >>> 4;//next_b右移4位

        return (byte) ((r_l | r_n) & 0x3f);
    }

    public static byte thirdByte(byte last_b, byte next_b) {
        //取last_b的低4位和next_b的高2位
        int r_l = last_b & 0xff;
        int r_n = next_b & 0xff;
        r_l = r_l & 0x0f;//last_b去掉高4位
        r_l = r_l << 2;//last_b左移2位
        r_n = r_n >>> 6;//next_b右移6位

        return (byte) ((r_l | r_n) & 0x3f);
    }

    public static byte fourthByte(byte b) {
        //取b的低6位
        int r_b = b & 0xff;
        r_b = r_b << 2;
        r_b = r_b >>> 2;

        return (byte) (r_b & 0x3f);
    }

    public static byte lastOneByte(byte b, int move) {
        int r_b = b & 0xff;
        r_b = r_b << move;
        r_b = r_b >>> 2;
        return (byte) (r_b & 0x3f);
    }

    /******************************结束写代码******************************/


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String res;

        String _source;
        try {
            _source = in.nextLine();
        } catch (Exception e) {
            _source = null;
        }

        res = encode(_source.getBytes());
        System.out.println(res);
    }
}
