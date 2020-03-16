package concurrent.thread;


/**
 * demo for 传统线程通信 notify and wait
 * @author Richard_yyf
 * @version 1.0 2019/6/30
 */
public class NotifyWaitExample {

    /**
     * bank account
     */
    private class Account {

        private double balance = 0;
        /** 账户是否有钱 */
        private boolean flag  = false;

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public void draw() {
            synchronized (this) {
                if (!flag) {
                    // 没有钱 取钱方法阻塞
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    // 执行取钱操作
                    System.out.println(Thread.currentThread().getName()
                    + " 取出 金额 : " + balance);
                    balance -= balance;
                    // 没钱了
                    flag = false;
                    System.out.println("当前存款: " + balance);
                    notifyAll();
                }
            }
        }

        public void deposit(double depositAmount) {
            synchronized (this) {
                if (flag) {
                    // 有钱 存钱方法阻塞
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    // 没钱 开始存钱操作
                    System.out.println(Thread.currentThread().getName()
                            + " 存入 金额 : " + depositAmount);
                    balance += depositAmount;
                    // 有钱了
                    flag = true;
                    System.out.println("当前存款: " + balance);
                    notifyAll();
                }
            }
        }
    }

    /**
     * 取钱者
     */
    private class DrawThread extends Thread {
        private Account account;

        public DrawThread(Account account, String name) {
            super(name);
            this.account = account;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i ++) {
                account.draw();
            }
        }
    }

    /**
     * 存钱这
     */
    private class DepositThread extends Thread {
        private Account account;

        public DepositThread(Account account, String name) {
            super(name);
            this.account = account;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i ++) {
                account.deposit(1000);
            }
        }
    }

    public static void main(String[] args) {
        NotifyWaitExample example = new NotifyWaitExample();
        example.testCase();
    }

    private void testCase() {
        Account account = new Account();
        new DrawThread(account, "取款者 A").start();
        new DepositThread(account, "存款者 1").start();
        new DepositThread(account, "存款者 2").start();
        new DepositThread(account, "存款者 3").start();
        // 程序不会停止，需要手动终止
    }
}
