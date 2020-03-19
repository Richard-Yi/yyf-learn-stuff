package main.java.concurrent.thread;

/**
 * 帮助理解 sleep 不会让出监视器锁资源
 *
 * 在线程A睡眠的这10s内obj的监视器锁还是线程A自己持有
 * 线程B会一直阻塞直到线程A醒来后退出synchronize代码块 释放锁
 *
 * @author Richard_yyf
 * @version 1.0 2020/3/12
 */
public class ThreadSleepDemo {


    private static final Object obj = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            // 获取独占锁
            synchronized (obj) {
                try {
                    System.out.println("child thread A is in sleep");
                    Thread.sleep(10000);
                    System.out.println("child thread A is awake");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            // 获取独占锁

            synchronized (obj) {
                try {
                    System.out.println("child thread B is in sleep");
                    Thread.sleep(10000);
                    System.out.println("child thread B is awake");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}