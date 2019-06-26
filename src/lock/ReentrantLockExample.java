package lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

    public static void main(String[] args) {
        case1();
//        case2();
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
