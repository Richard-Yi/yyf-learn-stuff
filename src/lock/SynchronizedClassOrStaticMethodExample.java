package lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 同一个类 或者 同步一个静态方法
 * @author Richard_yyf
 * @version 1.0 2019/6/26
 */
public class SynchronizedClassOrStaticMethodExample {

    private static final int TIMES = 10;

    private final static int param = 1231;

    public void func1() {

        synchronized (SynchronizedClassOrStaticMethodExample.class) {
            for (int i = 0; i < TIMES; i++) {
                System.out.print(i + " ");
            }
            System.out.println("func1 finished");
        }
    }

    public synchronized static void synchronizedStaticMethod() {

        for (int i = 0; i < TIMES; i++) {
            System.out.print(i + " ");
        }
        System.out.println("synchronizedStaticMethod finished");

    }

    public static void func3() {
        for (int i = 0; i < TIMES; i++) {
            System.out.println(i + " " + param + " ");
        }
        System.out.println("func3 finished");
    }

    public void func4() {
        for (int i = 0; i < TIMES; i++) {
            System.out.println(i + " " + "func4" + " ");
        }
        System.out.println("func4 finished");
    }


    public static void main(String[] args) {
//        case1();
        case2();
    }

    private static void case1() {
        SynchronizedClassOrStaticMethodExample example1 = new SynchronizedClassOrStaticMethodExample();
        SynchronizedClassOrStaticMethodExample example2 = new SynchronizedClassOrStaticMethodExample();
        ExecutorService exector = Executors.newCachedThreadPool();
        // 往线程池中放两个任务 按照 缓存线程池的机制，会有两个线程执行
        exector.execute(example1::func1);
        exector.execute(example2::func1);
//        exector.execute(example::func2);
    }

    private static void case2() {
        SynchronizedClassOrStaticMethodExample example = new SynchronizedClassOrStaticMethodExample();
        ExecutorService exector = Executors.newCachedThreadPool();
        // 往线程池中放两个任务 按照 缓存线程池的机制，会有两个线程执行
        exector.execute(SynchronizedClassOrStaticMethodExample::synchronizedStaticMethod);
//        exector.execute(SynchronizedClassOrStaticMethodExample::synchronizedStaticMethod);
        exector.execute(example::func4);
    }

    //  after debugging the code, found that the two threads are both waiting,
    //  which cause tha fact that the main thread don't stop and keeps running.
    //  exector.execute(SynchronizedClassOrStaticMethodExample::synchronizedStaticMethod);
    //  exector.execute(example::func4);
}