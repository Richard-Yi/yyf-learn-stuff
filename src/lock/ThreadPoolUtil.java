package lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Richard_yyf
 * @version 1.0 2019/6/26
 */
public class ThreadPoolUtil {

    private final static long AWAIT_TIME = 5 * 1000;

    public static void tryReleasePool(ExecutorService exector) {

        try {
            exector.shutdown();

            if(!exector.awaitTermination(AWAIT_TIME, TimeUnit.MILLISECONDS)){
                // 超时的时候向线程池中所有的线程发出中断(interrupted)。
                exector.shutdownNow();
            }
        } catch (InterruptedException e) {
            // awaitTermination方法被中断的时候也中止线程池中全部的线程的执行。
            System.out.println("awaitTermination interrupted: " + e);
            exector.shutdownNow();
        }
    }
}
