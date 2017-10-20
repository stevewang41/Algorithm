package job.xiecheng._1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by wangshiyi on 17/9/21.
 */

public class Main {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static int[] twoSum(int[] nums, int target) {
        int[] result = {-1, -1};
        Map<Integer, Integer> elementToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (elementToIndex.containsKey(target - nums[i]) && elementToIndex.get(target - nums[i]) < i) {
                result[0] = elementToIndex.get(target - nums[i]);
                result[1] = i;
                return result;
            }
            elementToIndex.put(nums[i], i);
        }
        return result;

    }

    /******************************结束写代码******************************/


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] res;

        int _nums_size = 0;
        _nums_size = Integer.parseInt(in.nextLine().trim());
        int[] _nums = new int[_nums_size];
        int _nums_item;
        for (int _nums_i = 0; _nums_i < _nums_size; _nums_i++) {
            _nums_item = Integer.parseInt(in.nextLine().trim());
            _nums[_nums_i] = _nums_item;
        }

        int _target;
        _target = Integer.parseInt(in.nextLine().trim());

        res = twoSum(_nums, _target);
        for (int res_i = 0; res_i < res.length; res_i++) {
            System.out.println(String.valueOf(res[res_i]));
        }

    }
}
