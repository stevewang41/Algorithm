package job.zuoyebang;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * Created by wangshiyi on 17/9/29.
 */

public class Main {

    public static void main(String[] args) {
        String input = "abca";
        HashMap<Character, Integer> charToNum = new HashMap<Character, Integer>();
        TreeMap<Integer, LinkedList<Character>> numToList = new TreeMap<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int num;
            if (charToNum.containsKey(c)) {
                num = charToNum.get(c) + 1;
                LinkedList<Character> list = numToList.get(num);
                list.remove(c);
                numToList.put(num, list);
            } else {
                num = 1;
            }
            charToNum.put(c, num);
            LinkedList<Character> list;
            if (numToList.containsKey(num)) {
                list = numToList.get(num);
            } else {
                list = new LinkedList<Character>();
            }
            list.add(c);
            numToList.put(num, list);
        }
        Iterator<Integer> it = numToList.keySet().iterator();
        int i = 0;
        while (it.hasNext() && i == 0) {
            int num = it.next();
            i++;
            for (char c : numToList.get(num)) {
                System.out.println(c);
            }
        }
    }
}