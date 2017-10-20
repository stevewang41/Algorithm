package job.sohu;


import java.util.ArrayList;
import java.util.Scanner;

public class Main1 {
    public static ArrayList<Integer> getResult(ArrayList<Integer[]> list) {

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int V = 0;
            for (int j = 0; j < list.get(0).length; j++) {
                V += list.get(i)[j] * (j + 1) * (j + 1) * (j + 1);
            }
            int num = V / (6 * 6 * 6);
            if (V % (6 * 6 * 6) != 0)
                num = num + 1;
            result.add(num);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer[]> list = new ArrayList<>();
        while (sc.hasNextInt()) {
            Integer[] array = new Integer[6];
            boolean flag = false;
            for (int i = 0; i < 6; i++) {
                array[i] = sc.nextInt();
                if (array[i] != 0)
                    flag = true;
            }
            if (flag == true)
                list.add(array);
            else
                break;
        }
        ArrayList<Integer> result = getResult(list);
        for (int i = 0; i < result.size()-1; i++) {
            System.out.println(result.get(i));
        }
        System.out.print(result.get(result.size()-1));
    }
}