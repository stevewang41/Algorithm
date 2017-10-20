/**
 * Created by wangshiyi on 17/8/17.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class LRU {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int cacheSize = scan.nextInt();
            ArrayList<Integer> taskList = new ArrayList<>();
            int page = scan.nextInt();
            while (page != -1) {    // 输入结束标志
                taskList.add(page);
                page = scan.nextInt();
            }
            System.out.println(getMissingNum(cacheSize, taskList));
        }
        scan.close();
    }

    public static int getMissingNum(int cacheSize, ArrayList<Integer> taskList) {
        int[] cacheQueue = new int[cacheSize];   // 缓存队列，用数组模拟
        int tail = -1;          // 缓存队列的尾指针
        int missingNum = 0;     // 缺页次数
        boolean isMissing;      // 缺页标志
        for (int page : taskList) {
            isMissing = true;
            for (int j = 0; j <= tail; j++) {   // 从头到尾遍历缓存队列
                if (cacheQueue[j] == page) {    // 命中
                    isMissing = false;
                    for (int k = j + 1; k <= tail; k++) {
                        cacheQueue[k - 1] = cacheQueue[k];
                    }
                    cacheQueue[tail] = page;  // 最近命中的页面移至队尾，使它最后一个被淘汰
                    break;
                }
            }
            if (isMissing) {  // 缺页
                missingNum++;
                if (tail == cacheSize - 1) {      // 缓存已满
                    for (int k = 1; k <= tail; k++) {
                        cacheQueue[k - 1] = cacheQueue[k];  // 移出最久未使用的队首页面
                    }
                    cacheQueue[tail] = page;      // 并将新页面加入到队尾
                } else {    // 缓存未满
                    cacheQueue[++tail] = page;
                }
            }
        }
        return missingNum;
    }

}