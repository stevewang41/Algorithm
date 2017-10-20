package job.alibaba;

import java.util.Scanner;

/**
 * Created by wangshiyi on 17/8/25.
 */


public class Bottle {

    /**
     * 请完成下面这个函数，实现题目要求的功能
     * 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^
     **/
    static int maxMount(int price, int cap, int emptyBottle, int money) {

        int res = money / price;
        int capNum = res;
        int emptyNum = res;
        while (capNum / cap > 0 || emptyNum / emptyBottle > 0) {
            int get = capNum / cap + emptyNum / emptyBottle;
            res += get;
            capNum = capNum % cap + get;
            emptyNum = emptyNum % emptyBottle + get;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int res;

        int _price;
        _price = Integer.parseInt(in.nextLine().trim());

        int _cap;
        _cap = Integer.parseInt(in.nextLine().trim());

        int _emptyBottle;
        _emptyBottle = Integer.parseInt(in.nextLine().trim());

        int _money;
        _money = Integer.parseInt(in.nextLine().trim());

        res = maxMount(_price, _cap, _emptyBottle, _money);
        System.out.println(String.valueOf(res));
    }
}