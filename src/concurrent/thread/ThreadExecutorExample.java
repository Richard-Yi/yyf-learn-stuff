package concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Richard_yyf
 * @version 1.0 2019/6/23
 */
public class ThreadExecutorExample {

    public static void main(String[] args) {

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);

        ExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        ExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    }


}
