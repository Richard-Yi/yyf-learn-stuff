package concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *
 * @author Richard_yyf
 * @version 1.0 2019/7/1
 */
public class FutureTaskExample {

    public static void main(String[] args) {
        FutureTaskExample example = new FutureTaskExample();
        example.testCase();
    }

    private void testCase() {
        // 这个任务会耗时1s左右
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            int result = 0;
            for (int i = 0; i < 100; i++) {
                Thread.sleep(10);
                result += i;
            }
            return result;
        });

        // 主线程启动异步任务
        System.out.println("main thread 启动异步线程 执行任务");
        Thread computeThread = new Thread(futureTask);
        computeThread.start();

        // 主线程 完成自己的任务之后，再去回去异步任务的结果
        // do some specific logic here
        System.out.println("main thread 正在执行自己的任务");

        try {
            // Waits if necessary for the computation to complete,
            // and then retrieves its result.
            // 如果还没好，就会一直等待
            long before = System.currentTimeMillis();
            System.out.println("主线程等待获取异步线程的计算结果, current time " + before);
            System.out.println("主线程成功获取线程的计算结果: " + futureTask.get());
            long after = System.currentTimeMillis();

            System.out.println("finished, current time: " + after);
            System.out.println("异步线程耗时, time elapsed: " + (after - before));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
