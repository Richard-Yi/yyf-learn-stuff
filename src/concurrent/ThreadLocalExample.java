package concurrent;

import concurrent.lock.ThreadPoolUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Richard_yyf
 * @version 1.0 2019/7/3
 */
public class ThreadLocalExample {

    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        ExecutorService service = Executors.newCachedThreadPool();

        service.execute(() -> {
            threadLocal.set(1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 不会收到线程2的影响，因为ThreadLocal 线程本地存储
            System.out.println(Thread.currentThread().getName() + " get " + threadLocal.get());
            threadLocal.remove();
        });

        service.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " set 2");
            threadLocal.set(2);
            threadLocal.remove();
        });

        ThreadPoolUtil.tryReleasePool(service);
    }
}
