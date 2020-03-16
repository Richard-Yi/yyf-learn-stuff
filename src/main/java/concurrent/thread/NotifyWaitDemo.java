package main.java.concurrent.thread;

/**
 * @author Richard_yyf
 * @version 1.0 2020/3/11
 */
public class NotifyWaitDemo {

    public static final int MAX_SIZE = 1024;
    // 共享变量
    public static Queue queue = new Queue();

    public static void main(String[] args) {
        // 生产者
        Thread producer = new Thread(() -> {
            synchronized (queue) {
                while (true) {
                    // 挂起当前线程（生产者线程）
                    // 并且，释放通过queue的监视器锁，让消费者对象获取到锁，执行消费逻辑
                    if (queue.size() == MAX_SIZE) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // 空闲则生成元素，并且通知消费线程
                    queue.add();
                    queue.notifyAll();
                }
            }
        });
        // 消费者
        Thread consumer = new Thread(() -> {
            synchronized (queue) {
                while (true) {
                    // 挂起当前线程（消费者线程）
                    // 并且，释放通过queue的监视器锁，让生产者对象获取到锁，执行生产逻辑
                    if (queue.size() == 0) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // 空闲则消费元素，并且通知生产线程
                    queue.take();
                    queue.notifyAll();
                }
            }
        });
        producer.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consumer.start();
    }

    static class Queue {

        private int size = 0;

        public int size() {
            return this.size;
        }

        public void add() {
            // TODO
            size++;
            System.out.println("执行add 操作，current size: " +  size);
        }

        public void take() {
            // TODO
            size--;
            System.out.println("执行take 操作，current size: " +  size);
        }
    }
}
