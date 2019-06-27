package lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Synchronized 同步代码块/方法 知识点示例
 * 同步代码块 和 同步方法原理相同
 * <>
 * 1. synchronized (this) 这样的表达，锁的是实例，线程执行到了同步代码块或者非static同步方法的时候，
 * 就获取到了这个临界区的锁，其他线程进入到等待状态，无法进入该临界区。
 * </>
 *
 * @author Richard_yyf
 * @version 1.0 2019/6/26
 */
public class SynchronizedCodeBlockExample {

    private static final int TIMES = 10;

    private int param = 1231;

    public void func1() {
        synchronized (this) {
            for (int i = 0; i < TIMES; i++) {
                System.out.print(i + " ");
            }
            System.out.println("thread sleep 30s");
            try {
                Thread.sleep(30 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void func2() {
        for (int i = 0; i < TIMES; i++) {
            System.out.println(i + " ");
        }
    }

    public void func3() {

        for (int i = 0; i < TIMES; i++) {
            System.out.println(i + " " + param + " ");
        }

    }

    public static void main(String[] args) {
        case1();
//        case2();
    }


    /**
     * test case 1： 多个线程 调用 多个对象的代码块
     * 因为代码块被同步了，所以是依次还行
     * output: 乱序输出 如 [0 1 2 3 0 1 2 3 4 5 6 7 8 9 4 5 6 7 8 9]
     */
    private static void case2() {
        SynchronizedCodeBlockExample example1 = new SynchronizedCodeBlockExample();
        SynchronizedCodeBlockExample example2 = new SynchronizedCodeBlockExample();
        ExecutorService exector = Executors.newCachedThreadPool();
        // 往线程池中放两个任务 按照 缓存线程池的机制，会有两个线程执行
        exector.execute(example1::func1);
        exector.execute(example2::func2);

        ThreadPoolUtil.tryReleasePool(exector);
    }

    /**
     * test case 1： 多个线程 调用 同一个对象的代码块
     * 因为代码块被同步了，所以是依次还行
     * output: 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9
     */
    private static void case1() {
        SynchronizedCodeBlockExample example = new SynchronizedCodeBlockExample();
        ExecutorService exector = Executors.newCachedThreadPool();
        // 往线程池中放两个任务 按照 缓存线程池的机制，会有两个线程执行
        exector.execute(example::func1);
//        exector.execute(example::func1);
//        exector.execute(example::func2);
        exector.execute(example::func3);

        ThreadPoolUtil.tryReleasePool(exector);
    }
}
