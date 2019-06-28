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

    private class InvokeThread extends Thread {

        private JoinThread joinThread;

        InvokeThread(JoinThread joinThread) {
            this.joinThread = joinThread;
        }

        @Override
        public void run() {
            System.out.println("InvokeThread start");
            try {
                //
                joinThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("InvokeThread end");
        }
    }

    public static void main(String[] args) {
        JoinExample example = new JoinExample();
        example.test();
    }

    private void test() {
       for (int i = 0; i < TIMES; i++) {
           if (i == 20) {
               JoinThread jt = new JoinThread("被join 的线程");
               jt.start();
               // main 线程调用了jt线程的join()方法
               // main 线程必须等到 jt 执行完之后才会向下执行
               try {
                   jt.join();
                   // join(long mills) - 等待时间内 被join的线程还没执行，不再等待
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           System.out.println(Thread.currentThread().getName() + "  " + i);
        }
    }
}
