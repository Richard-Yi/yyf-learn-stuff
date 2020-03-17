package concurrent.lock;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * await(), signal(), signalAll()
 * @author Richard_yyf
 * @version 1.0 2019/6/30
 */
public class LockConditionWaitAndSignalExample {

    /**
     * bank account
     */
    private class Account {

        private final Lock lock = new ReentrantLock();

        /** account not empty */
        private final Condition notEmpty = lock.newCondition();
        /** account not full */
        private final Condition notFull = lock.newCondition();

        /** account not full */
        private double restrict = 1000;

        private double balance = 0;

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }


        public void draw() {
            try {
                lock.lock();
                if (balance <= 0) {
                    // 现在没钱 等到有钱 再往下走
                    System.out.println(Thread.currentThread().getName()
                            + "现在没钱 notEmpty 条件不满足 await");
                    notEmpty.await();
                }
                // 执行取钱操作
                System.out.println(Thread.currentThread().getName()
                        + " 取出 金额 : " + 100);
                balance -= 100;
                System.out.println("当前存款: " + balance);
                // 取过钱 肯定不是full的了
                System.out.println(Thread.currentThread().getName()
                        + "取过钱了，notFull 条件满足 通知" );
                notFull.signalAll();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void deposit(double depositAmount) {
            try {
                lock.lock();
                if (balance >= restrict) {
                    // 存款达到上限 notFull 条件不满足 await
                    System.out.println(Thread.currentThread().getName()
                            + "存款达到上限 notFull 条件不满足 await");
                    notFull.await();
                }
                // 没钱 开始存钱操作
                System.out.println(Thread.currentThread().getName()
                        + " 存入 金额 : " + depositAmount);
                balance += depositAmount;
                // 有钱了
                System.out.println("当前存款: " + balance);
                // 存过钱了，notEmpty 条件满足 通知
                System.out.println(Thread.currentThread().getName()
                        + "存过钱了，notEmpty 条件满足 通知" );
                notEmpty.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * 取钱者
     */
    private class DrawTask implements Runnable {

        private Account account;

        public DrawTask(Account account) {
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
    private class DepositTask implements Runnable {

        private Account account;

        public DepositTask(Account account) {
            this.account = account;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i ++) {
                account.deposit(200);
            }
        }
    }

    public static void main(String[] args) {
        LockConditionWaitAndSignalExample example = new LockConditionWaitAndSignalExample();
        example.testCase();
    }

    private void testCase() {
        ExecutorService draw = Executors.newCachedThreadPool();
        ExecutorService deposite = Executors.newCachedThreadPool();

        Account account = new Account();
        draw.execute(new DrawTask(account));
        deposite.execute(new DepositTask(account));
        deposite.execute(new DepositTask(account));

        concurrent.lock.ThreadPoolUtil.tryReleasePool(draw);
        concurrent.lock.ThreadPoolUtil.tryReleasePool(deposite);
    }

}
