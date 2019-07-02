package concurrent;

import concurrent.lock.ThreadPoolUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Richard_yyf
 * @version 1.0 2019/7/2
 */
public class AtomicSynchronizedExample {

    private static int atomicCount = 0;

    private static int count = 0;

    private synchronized static void add() {
        atomicCount++;
    }

    public static void main(String[] args) {
        final int threadSize = 1000;
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {
            executor.execute(() -> {
                add();
                count++;
                countDownLatch.countDown();
            });
        }
        System.out.println("atomicCount: " + atomicCount);
        System.out.println("count: " + count);

        ThreadPoolUtil.tryReleasePool(executor);
    }
}
