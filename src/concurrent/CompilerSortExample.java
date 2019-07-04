package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 学习 编译器重排序的相关知识
 * 和一些猜想的验证
 *
 * @author Richard_yyf
 * @version 1.0 2019/7/4
 */
public class CompilerSortExample {

    private static int count = 0;
//    private static volatile int count = 0;

    private boolean stop = false;


    public static void main(String[] args) {
        CompilerSortExample example = new CompilerSortExample();
//        example.testCase();
        example.testCase2();
    }

    private void testCase2() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(this::run);
        try {
            Thread.sleep(2000);
            setToStop();
            Thread.sleep(2000);
            System.out.println("finish main");
            System.out.println(stop);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void setToStop() {
        stop = true;
    }

    private void testCase() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            while (count < 10) {
                count++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.execute(() -> {
            while (count < 10) {
                System.out.println(count);
            }
        });
    }

    public void run() {
        int i = 0;
        // 为提高存取速度，编译器会先把变量读取到一个寄存器中；以后再取该变量值时，就直接从寄存器中取，不会再从内存中取值了。
        // 如果别的线程修改了内存中变量的值，那么由于寄存器中的变量值一直没有发生改变，很有可能会导致循环不能结束。
        // C2 compiler
        while (!stop) {
            i++;
        }
        System.out.println("finish loop,i=" + i);
    }


}
