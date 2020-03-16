package main.java.concurrent.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 帮助理解 sleep 不会让出监视器资源
 *
 * 在线程A睡眠的这10s内那个独占锁lock还是线程A自己持有
 * 线程B会一直阻塞直到线程A醒来后执行unlock释放锁。
 *
 * @author Richard_yyf
 * @version 1.0 2020/3/12
 */
public class ThreadSleepDemo {

    // 独占锁
    private static final Lock LOCK = new ReentrantLock();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            // 获取独占锁
            LOCK.lock();
            try {
                System.out.println("child thread A is in sleep");
                Thread.sleep(10000);
                System.out.println("child thread A is awake");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // release lock
                LOCK.unlock();
            }
        });

        Thread threadB = new Thread(() -> {
            // 获取独占锁
            LOCK.lock();
            try {
                System.out.println("child thread B is in sleep");
                Thread.sleep(10000);
                System.out.println("child thread B is awake");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // release lock
                LOCK.unlock();
            }
        });

        threadA.start();
        threadB.start();
    }
}
