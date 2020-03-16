package concurrent.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Richard_yyf
 * @version 1.0 2019/6/23
 */
public class ThreadUseExample {

    // 3 ways to use Thread
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnble = () -> System.out.println("hello ThreadUseExample");
        Thread thread = new Thread(runnble);
        thread.start();

        Callable<String> callable = () -> "hello callable";
        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread aThread = new Thread(futureTask);
        aThread.start();
        System.out.println("result of callale is " + futureTask.get());

        Thread myThread = new MyThread();
        myThread.start();

    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("This is my thread running ");
        }
    }
}
