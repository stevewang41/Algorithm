package job.toutiao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by wangshiyi on 17/8/22.
 */

public class Main1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        TreeMap<Integer, Integer> xToY = new TreeMap<>();
        HashMap<Integer, Integer> yToX = new HashMap<>(num);
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int x;
        int y;
        for (int i = 0; i < num; i++) {
            x = in.nextInt();
            y = in.nextInt();
            xToY.put(x, y);
            yToX.put(y, x);
            if (x > maxX) {
                maxX = x;
            }
            if (y > maxY) {
                maxY = y;
            }
        }
//        xToY.remove(maxX);
//        xToY.remove(yToX.get(maxY));
//        yToX.remove(maxY);
//        yToX.remove(xToY.get(maxX));

        Iterator<Integer> it = xToY.keySet().iterator();
        while (it.hasNext()) {
            int x1 = it.next();
            int y1 = xToY.get(x1);
            if ((x1 < maxX && y1 < xToY.get(maxY)) || (y1 < maxY && x1 < yToX.get(maxY))) {
                xToY.remove(x1);
                yToX.remove(y1);
            }
        }

    }


}
