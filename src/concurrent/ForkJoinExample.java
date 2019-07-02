package concurrent;

import java.util.concurrent.*;

/**
 * split a big calculation task into small ones
 * and the small tasks will run in parallel
 * <p>
 * <>
 * Java 7提供了 ForkJoinPool来支持将一个任务拆分成多个“小任务”并行计算，再把多个“小任务” 的结果合并成总的计算结果。
 * ForkJoinPool是ExecutorService的实现类，因此是一种特殊的线程池。
 * <p>
 * 创建了 ForkJoinPool 实例之后，就可调用 ForkJoinPool 的 submit(ForkJoinTask task)
 * 或 invoke (ForkJoinTask task)方法来执行指定任务了。其中ForkJoinTask代表一个可以并行、合并的任务。
 * ForkJoinTask 是一个抽象类，它还有两个抽象子类：RecursiveAction 和 RecursiveTask。
 * 其中 RecursiveTask 代表有返回值的任务，而RecursiveAction代表没有返回值的任务。
 * </>
 *
 * @author Richard_yyf
 * @version 1.0 2019/7/1
 */
public class ForkJoinExample {

    // 如果不需要返回值，就集成RecursiveAction (RecursiveAction extends ForkJoinTask<Void>，是没有返回值的)

    private class MyForkJoinTask extends RecursiveTask<Integer> {

        private final int threshold = 5;

        private int first;

        private int last;

        public MyForkJoinTask(int first, int last) {
            this.first = first;
            this.last = last;
        }

        @Override
        protected Integer compute() {
            int result = 0;
            // Q: 这个拆分任务的逻辑是自己在compute 方法里设计的吗？
            System.out.println(Thread.currentThread().getName());
            if (last - first <= threshold) {
                // 任务足够小 则直接计算
                for (int i = first; i <= last; i++) {
                    result += i;
                }
            } else {
                // 拆分小任务 简单二分逻辑
                int middle = (first + last) / 2;
                MyForkJoinTask leftTask = new MyForkJoinTask(first, middle);
                MyForkJoinTask rightTask = new MyForkJoinTask(middle, last);
                leftTask.fork();
                rightTask.fork();
                result = leftTask.join() + rightTask.join();
            }
            return result;
        }
    }

    public static void main(String[] args) {
        ForkJoinExample example = new ForkJoinExample();
        example.testCase();
    }

    private void testCase() {
        // 计算 1+2+...+9999+10000
        MyForkJoinTask forkJoinTask = new MyForkJoinTask(1, 500);
        // 并行任务ForkJoinTask 是用 ForkJoinPool 来执行的
        // new ForkJoinPool(): 以Runtime.getRuntime().availableProcessors()返回值为parallelism 的值来创建ForkJoinPool.
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> result = forkJoinPool.submit(forkJoinTask);
//        ForkJoinTask<Integer> result2 = forkJoinPool.submit(forkJoinTask);
        try {
            System.out.println(result.get());
//            System.out.println(result2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
