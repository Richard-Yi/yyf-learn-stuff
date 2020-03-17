package concurrent.thread;

/**
 * @author Richard_yyf
 * @version 1.0 2019/6/28
 */
public class JoinExample {

    private static final int TIMES = 100;

    private class JoinThread extends Thread {

        JoinThread(String name){
           super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < TIMES; i++) {
                System.out.println(getName() + " " + i);
            }
        }
    }

    public static void main(String[] args) {
        JoinExample example = new JoinExample();
        example.test();
    }

    private void test() {
       for (int i = 0; i < TIMES; i++) {
           if (i == 20) {
               Thread jt1 = new JoinThread("子线程1");
               Thread jt2 = new JoinThread("子线程2");
               jt1.start();
               jt2.start();
               // main 线程调用了jt线程的join()方法
               // main 线程必须等到 jt 执行完之后才会向下执行
               try {
                   jt1.join();
                   jt2.join();
                   // join(long mills) - 等待时间内 被join的线程还没执行，不再等待
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           System.out.println(Thread.currentThread().getName() + "  " + i);
        }
    }
}
