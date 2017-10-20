package job.alibaba;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by wangshiyi on 17/8/16.
 * <p>
 * 跑的快是一款老少皆宜的牌类游戏，这里暂时考虑一副牌4个人玩的情况，基本规则如下：
 * 扣除大小王，每个人13张牌，不考虑花色
 * 单牌：一张一张的出牌，大小顺序是2>A>K>Q>J>10>9>8>7>6>5>4>3。
 * 顺子：5张以上连续的单牌，最大JQKA2，最小A2345
 * 对子：成双出牌，大小顺序：2对>A对>K对>Q对>J对>10对>9对>8对>7对>6对>5对>4对>3对。
 * 连对：两个及以上相连的对子比如：2233 778899 其中2233也可以连，但是是最小的连队，AA2233是最小的三连对。
 * 三带一：三张相同的牌可带一张单牌，最大三个2，三个对应牌大过对方即可，带1个的随机。
 * 炸弹：四个相同的牌，可以大过其他牌，最大四个2
 * 为简化起见
 * 1，输入输出，10用字符I表示，且所有字符都大写
 * 2，出牌时，对于能大过的请输出最小的能大过的牌即可
 * 3，本次答题可以暂不考虑三带一规则
 * <p>
 * 编译器版本: Java 1.8.0_66
 * 请使用标准输入输出(System.in, System.out)；已禁用图形、文件、网络、系统相关的操作，如java.lang.Process , javax.swing.JFrame , Runtime.getRuntime；不要自定义包名称，否则会报错，即不要添加package answer之类的语句；您可以写很多个类，但是必须有一个类名为Main，并且为public属性，并且Main为唯一的public class，Main类的里面必须包含一个名字为'main'的静态方法（函数），这个方法是程序的入口
 * 时间限制: 3S (C/C++以外的语言为: 5 S)   内存限制: 128M (C/C++以外的语言为: 640 M)
 * 输入:
 * 共2行 第一行是你最初抓的13张牌 第二行表示你上家出的牌
 * 输出:
 * 如果能大过，则输出你的出牌，否则输出0
 * 输入范例:
 * 6788999IJKKAA
 * 3344
 * 输出范例:
 * 8899
 */

public class PuKe {

    private static HashMap<Character, Integer> map = new HashMap<Character, Integer>() {
        {
            put('3', 3);
            put('4', 4);
            put('5', 5);
            put('6', 6);
            put('7', 7);
            put('8', 9);
            put('9', 9);
            put('I', 10);
            put('J', 11);
            put('Q', 12);
            put('K', 13);
            put('A', 14);
            put('2', 15);
        }
    };

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String myCards = in.next();
        String lastCards = in.next();

        switch (lastCards.length()) {

            case 1:
                int card = map.get(lastCards.charAt(0));
                int minDiff = 100;
                int minIndex = 100;
                for (int i = 0; i < 13; i++) {
                    int diff = map.get(myCards.charAt(i)) - card;
                    if (diff > 0 && diff < minDiff) {
                        minDiff = map.get(myCards.charAt(i)) - card;
                        minIndex = i;
                    }
                }
                System.out.println(myCards.charAt(minIndex));
                break;
            case 2:
                int card2 = map.get(lastCards.charAt(0));
                int minDiff2 = 100;
                int minIndex2 = 100;
                for (int i = 0; i < 13; i++) {
                    int diff = map.get(myCards.charAt(i)) - card2;
                    if (diff > 0 && diff < minDiff2) {
                        minDiff2 = map.get(myCards.charAt(i)) - card2;
                        minIndex2 = i;
                    }
                }
                System.out.println(myCards.substring(minIndex2, minIndex2 + 1));
                break;

        }


    }
}
