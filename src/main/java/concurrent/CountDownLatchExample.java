package concurrent;

import concurrent.lock.ThreadPoolUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Example for CountDownLatch
 * 顾名思义，线程A给自己插了N个门闩，
 * 被等待的线程每执行一次countDown() 操作，就是拿掉一个门闩，
 * 当门闩全部拿掉的时候，即countDownLatch 内部计数器为0的时候，
 * 就是线程A继续运行的时候。
 * @author Richard_yyf
 * @version 1.0 2019/6/30
 */
public class CountDownLatchExample {
    public static void main(String[] args) {
        CountDownLatchExample example = new CountDownLatchExample();
        example.testCase();
    }

    private void testCase() {
        final int totalThreadCount = 10;
        CountDownLatch countDownLatch = new CountDownLatch(totalThreadCount);
        ExecutorService executor = Executors.newCachedThreadPool();
        AtomicInteger count = new AtomicInteger();
        List<Callable<Object>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(() -> {
                try {
                    // some specific logic here
                    System.out.println(Thread.currentThread().getName() + " is running");
                    count.getAndIncrement();
                } finally {
                    countDownLatch.countDown();
                }
                return null;
            });
        }
        // 主线程暂停 等待
        System.out.println("main thread current count value" + count);
        try {
            executor.invokeAll(list);
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread current count value" + count);

        ThreadPoolUtil.tryReleasePool(executor);
    }
}
