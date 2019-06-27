package lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 *
 * @author Richard_yyf
 * @version 1.0 2019/6/26
 */
public class ReentrantLockExample {

    private static final int TIMES = 10;

    private Lock lock = new ReentrantLock();

    public void func1() {
        lock.lock();
        try {
            for (int i = 0; i < TIMES; i++) {
                System.out.print(i + " ");
            }
        } finally {
            lock.unlock();
        }
    }

    public void sleepFunc1() {
        lock.lock();
        try {
            for (int i = 0; i < TIMES; i++) {
                System.out.print(i + " ");
            }
            System.out.println(Thread.currentThread().getName() + " be in sleep 10s");
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * ReentrantLock  Lock的高级应用，当持有锁的线程长期不释放锁的时候，正在等待的线程可以选择放弃等待，改为处理其他事情
     */
    public void func2() {
        boolean isLocked = false;
        try {
            try {
                // 超时返回false
                // 成功获取到临界区的锁 返回 true
                isLocked = lock.tryLock(1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                // 当被其他线程中断时抛出异常
                e.printStackTrace();
            }
            if (isLocked) {
                // .... specific logic here
                System.out.println(Thread.currentThread().getName() + " succeed to get lock now");
                for (int i = 0; i < TIMES; i++) {
                    System.out.print(i + " ");
                }
                // ....
            } else {
                System.out.println(Thread.currentThread().getName() + " fail get lock now");
            }
        } finally {
            if (isLocked) {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {
//        case1();
        case2();
    }

    /**
     * Lock 用法中的 "等待可中断" 场景
     */
    private static void case2() {
        ReentrantLockExample example = new ReentrantLockExample();
        ExecutorService exector = Executors.newCachedThreadPool();
        exector.execute(example::sleepFunc1);
        exector.execute(example::func2);

        ThreadPoolUtil.tryReleasePool(exector);
    }

    /**
     * out : 按序输出
     */
    private static void case1() {
        ReentrantLockExample example = new ReentrantLockExample();
        ExecutorService exector = Executors.newCachedThreadPool();
        exector.execute(example::func1);
        exector.execute(example::func1);

        ThreadPoolUtil.tryReleasePool(exector);
    }



}
