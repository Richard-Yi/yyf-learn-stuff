package lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Synchronized 同步代码块 知识点示例
 * @author Richard_yyf
 * @version 1.0 2019/6/26
 */
public class SynchronizedCodeBlockExample {

    private static final int TIMES = 10;

    public void func1() {
        synchronized (this) {
            for (int i = 0; i < TIMES; i++) {
                System.out.print(i + " ");
            }
        }
    }

    public static void main(String[] args) {
//        case1();
        case2();
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
        exector.execute(example2::func1);
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
        exector.execute(example::func1);
    }
}
