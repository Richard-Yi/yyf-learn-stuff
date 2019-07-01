package concurrent;

import concurrent.lock.ThreadPoolUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Example for CycleBarrier
 *
 * CycleBarrier is similar to CountDownLatch
 * a big difference compared to CountDownLatch is that CycleBarrier can use 'reset()' to reset the count
 * so that it could be used repeatedly
 *
 * That's why it is called cycle barrier (循环屏障)
 *
 * @author Richard_yyf
 * @version 1.0 2019/7/1
 */
public class CycleBarrierExample {

    private volatile AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) {
        CycleBarrierExample example = new CycleBarrierExample();
        example.testCase();
    }


    private void testCase() {
        final int totalThreadCount = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(totalThreadCount,
                () -> System.out.println("所有线程都到达屏障，可以执行接下去的操作"));

        ExecutorService executor = Executors.newCachedThreadPool();
        List<Callable<Object>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(() -> {
                try {
                    // some specific logic here
                    count.getAndIncrement();
                    System.out.println(Thread.currentThread().getName() + "执行屏障之前的操作, current count" + count);
                } finally {
                    System.out.println(Thread.currentThread().getName() + "到达屏障，开始等待");
                    cyclicBarrier.await();
                }
                // 屏障之后，统一执行的操作
                System.out.println(Thread.currentThread().getName() + "执行屏障之后的操作, current count" + count);

                return null;
            });
        }
        try {
            executor.invokeAll(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 复原
        cyclicBarrier.reset();

        ThreadPoolUtil.tryReleasePool(executor);

    }
}
