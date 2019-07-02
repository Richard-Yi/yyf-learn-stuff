package concurrent;

import concurrent.lock.ThreadPoolUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 内存模型三大特性 - 原子性验证对比
 *
 * @author Richard_yyf
 * @version 1.0 2019/7/2
 */
public class AtomicExample {

    private static AtomicInteger atomicCount = new AtomicInteger();

    private static int count = 0;

    private static void add() {
        atomicCount.incrementAndGet();
        count++;
    }

    public static void main(String[] args) {
        final int threadSize = 1000;
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {
            executor.execute(() -> {
                add();
                countDownLatch.countDown();
            });
        }
        System.out.println("atomicCount: " + atomicCount);
        System.out.println("count: " + count);

        ThreadPoolUtil.tryReleasePool(executor);
    }
}
