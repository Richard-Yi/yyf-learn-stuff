package main.java.concurrent.thread;

/**
 * @author Richard_yyf
 * @version 1.0 2020/3/17
 */
public class ThreadLifeTempTest {

    public static void main(String[] args) {
        Object object = new Object();

        new Thread(()->{
            synchronized (object) {
                try {
                    System.out.println("thread1 waiting");
                    // 等待10s
                    object.wait(10000);
                    System.out.println("thread1 after waiting");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread1").start();

        new Thread(()->{
            synchronized (object) {
                try {
                    // sleep也不会释放锁，所以thread1 不会获取到锁
                    Thread.sleep(10000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread2").start();
    }
}
