package concurrent;

import concurrent.lock.ThreadPoolUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Example for Semaphore
 *
 * Semaphore 类似于操作系统中的信号量，可以控制对互斥资源的访问线程数。
 *
 * @author Richard_yyf
 * @version 1.0 2019/7/1
 */
public class SemaphoreExample {

    public static void main(String[] args) {
        SemaphoreExample example = new SemaphoreExample();
        example.testCase();
    }

    private void testCase() {
        // 只允许3个客户端访问
        final int clientCount = 3;
        final int threadCount = 10;

        Semaphore semaphore = new Semaphore(clientCount);
        ExecutorService executor = Executors.newCachedThreadPool();

        List<Callable<Object>> list = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            list.add(() -> {
                try {
                    // Acquires a permit from this semaphore, blocking until one is available,
                    // or the thread is interrupted.
                    // 获取信号量允许
                    semaphore.acquire();
                    // some specific logic here
                    System.out.println(Thread.currentThread().getName()
                            + " 获取到 信号量允许，剩余 permits : " + semaphore.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // Releases a permit, returning it to the semaphore.
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName()
                            + " 归还 信号量允许，剩余 permits : " + semaphore.availablePermits());
                }
                return null;
            });
        }

        try {
            executor.invokeAll(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ThreadPoolUtil.tryReleasePool(executor);
    }
}
