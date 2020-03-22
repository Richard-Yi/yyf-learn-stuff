package concurrent;

import concurrent.lock.ThreadPoolUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal 造成脏数据的场景
 *
 * @author Richard_yyf
 * @version 1.0 2019/7/5
 */
public class ThreadLocalDirtyDataDemo {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(1);

        for (int i = 0; i < 2; i++) {
            MyThread thread = new MyThread();
            pool.execute(thread);
        }
        ThreadPoolUtil.tryReleasePool(pool);
    }

    private static class MyThread extends Thread {
        private static boolean flag = true;

        @Override
        public void run() {
            if (flag) {
                // 第一个线程set之后，并没有进行remove
                // 而第二个线程由于某种原因(这里是flag=false) 没有进行set操作
                String sessionInfo = this.getName();
                threadLocal.set(sessionInfo);
                flag = false;
            }
            System.out.println(this.getName() + " 线程 是 " + threadLocal.get());
            // 线程使用完threadLocal，要及时remove，这里是为了演示错误情况
        }
    }
}
