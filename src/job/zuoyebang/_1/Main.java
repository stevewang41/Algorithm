package job.zuoyebang._1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangshiyi on 17/9/26.
 * <p>
 * 设计一个函数统计指定文件中指定字符串出现的次数
 */

public class Main {

    private static final String PATH = "javatest/src/main/java/com/example/job/zuoyebang/_1/readme";
    private static final String WORD = "baidu";

    /**
     * 方法一：indexOf函数
     *
     * @param filePath
     * @param word
     * @return
     */
    public static int calWorkCount1(String filePath, String word) {

        int count = 0;
        File file = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            file = new File(filePath);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                int index;
                while ((index = line.indexOf(word)) != -1) {    // 一行当中可能有多个符合，所以需要双重循环
                    count++;
                    line = line.substring(index + word.length());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    /**
     * 方法二：正则匹配
     *
     * @param filePath
     * @param word
     * @return
     */
    public static int calWorkCount2(String filePath, String word) {

        int count = 0;
        File file = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            file = new File(filePath);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                Pattern p = Pattern.compile(word);
                Matcher m = p.matcher(line);
                while (m.find()) {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(calWorkCount2(PATH, WORD));
    }
}