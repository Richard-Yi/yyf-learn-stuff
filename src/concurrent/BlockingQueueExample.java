package concurrent;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Richard_yyf
 * @version 1.0 2019/7/1
 */
public class BlockingQueueExample {

    private static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(5);

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        testCase();
    }

    private static void testCase() {
        for (int i = 0; i < 2; i++) {
            Producer producer = new Producer("生产者 A-" + i);
            producer.start();
        }
        for (int i = 0; i < 5; i++) {
            Consumer consumer = new Consumer("消费者 A-" + i);
            consumer.start();
        }
        for (int i = 0; i < 3; i++) {
            Producer producer = new Producer("生产者 B-" + i);
            producer.start();
        }
    }

    private static class Consumer extends Thread {

        public Consumer(String name) {
            super(name);
        }

        @Override
        public void run() {
            String product = null;
            try {
                long before = System.currentTimeMillis();
                product = blockingQueue.take();
                System.out.println(Thread.currentThread().getName() + "获取 等待耗时 " + (System.currentTimeMillis() - before));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 从阻塞队列中取出 " + product
                    + "\n 队列 size " + blockingQueue.size());
        }
    }

    private static class Producer extends Thread {


        public Producer(String name) {
            super(name);
        }

        @Override
        public void run() {
            String product = "robot No." + count.getAndIncrement();
            try {
                Thread.sleep(1000);
                blockingQueue.add(product);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().getName() + " 往阻塞队列中放入 " + product + "发生异常"
                        + "\n 队列 size " + blockingQueue.size());
            }

            System.out.println(Thread.currentThread().getName() + " 往阻塞队列中放入 " + product
                    + "\n 队列 size " + blockingQueue.size());
        }
    }
}
