package concurrent.aqs;

import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Richard_Yi
 * @version 1.0 2020/4/30
 */
public class AqsDemo {

    private static LongAdder adder = new LongAdder();

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyTask());
        Thread thread2 = new Thread(new MyTask());
        Thread thread3 = new Thread(new MyTask());

        thread1.setName("thread-1");
        thread2.setName("thread-2");
        thread3.setName("thread-3");

        thread1.start();
        thread2.start();
        thread3.start();
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {

            lock.lock();

            System.out.println(Thread.currentThread().getName() + ": lock success");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.unlock();
        }
    }
}
