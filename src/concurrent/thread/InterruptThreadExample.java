package concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Richard_yyf
 * @version 1.0 2019/6/23
 */
public class InterruptThreadExample {
    private static class MyThread extends Thread {
        @Override
        public void run() {

//            System.out.println("Thread run");


            try {
                Thread.sleep(2000);
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new MyThread();
        thread1.start();
        thread1.interrupt();
        System.out.println("main run");

        // interrupt a specific thread in a ThreadPool
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<Object> future = executorService.submit(() -> "String");
        // interrupt the thread
        future.cancel(true);

    }
}
