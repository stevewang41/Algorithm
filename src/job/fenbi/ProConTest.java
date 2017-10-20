package job.fenbi;

import java.util.Random;
import java.util.Vector;

/**
 * Created by wangshiyi on 17/9/6.
 * <p>
 * #. 生产者消费者问题。生产者生成1~100的随机整数，消费者消费这个整数并打印。
 * 生产者有三个，分别以1秒、2秒、5秒的速度生成。
 * 消费者有两个，分别以1秒、3秒的速度消费。
 */

public class ProConTest {

    public static void main(String[] args) {
        Vector <Integer> stack = new Vector<>();
        new Thread(new Producer(stack, 1000)).start();
        new Thread(new Producer(stack, 2000)).start();
        new Thread(new Producer(stack, 5000)).start();

        new Thread(new Consumer(stack, 1000)).start();
        new Thread(new Consumer(stack, 3000)).start();
    }
}

class Producer implements Runnable {

    Vector<Integer> stack;
    long delay;

    public Producer(Vector<Integer> stack, long delay) {

        this.stack = stack;
        this.delay = delay;
    }

    public void produce() {
        synchronized (stack) {
            int value = new Random().nextInt(100);
            stack.add(value);
            stack.notifyAll();
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        produce();
    }
}


class Consumer implements Runnable {

    Vector<Integer> stack;
    long delay;

    public Consumer(Vector<Integer> stack, long delay) {

        this.stack = stack;
        this.delay = delay;
    }

    public void consume() {
        synchronized (stack) {
            while (stack.isEmpty()) {
                try {
                    stack.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int value = stack.firstElement();
            System.out.println("消费：" + value);
        }
    }


    @Override
    public void run() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consume();
    }
}
