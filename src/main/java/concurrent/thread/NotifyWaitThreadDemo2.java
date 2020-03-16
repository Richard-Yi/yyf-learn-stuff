package concurrent.thread;

/**
 * @author Richard_yyf
 * @version 1.0 2019/7/7
 */


public class NotifyWaitThreadDemo2 {
    public static void main(String[] args) {
        Resource res = new Resource();
        AddThread at = new AddThread(res);
        SubThread st = new SubThread(res);

        new Thread(at, "加法操作-1").start();
        new Thread(at, "加法操作-2").start();

        new Thread(st, "减法操作-1").start();
        new Thread(st, "减法操作-2").start();
    }
}

class Resource {
    private int num = 0;
    private boolean flag = true;

    public synchronized void add() throws Exception {
        if(!this.flag) {
            // flag为false只能执行减法操作
            super.wait();
        }
        this.num++;
        System.out.println(Thread.currentThread().getName() + "运行, num = " + this.num);
        // 可以进行减法操作
        this.flag = false;
        // 唤醒其他线程
        super.notifyAll();
    }
    public synchronized void sub() throws Exception {
        System.out.println(Thread.currentThread().getName() + "进入到 sub block");
        if(this.flag) {
            // flag为true只能进行加法操作,减法操作需要等待
            // 这里有可能出现先后两个减线程进入wait()状态的情况，
            // 之后被其他线程notifyAll()之后，就不会检查flag，然后执行两次-1的操作。
            System.out.println(Thread.currentThread().getName() + "wait");
            super.wait();
        }
        this.num--;
        System.out.println(Thread.currentThread().getName() + "运行, num = " + this.num);
        // 可以进行加法操作
        this.flag = true    ;
        // 唤醒其他线程
        super.notifyAll();
    }
}

class AddThread implements Runnable {
    private Resource res;

    public AddThread(Resource res) {
        this.res = res;
    }
    @Override
    public void run() {
        for (int i = 0 ; i < 50 ; i++) {
            try {
                this.res.add();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class SubThread implements Runnable {
    private Resource res;

    public SubThread(Resource res) {
        this.res = res;
    }

    @Override
    public void run() {
        for (int i = 0 ; i < 50 ; i++) {
            try {
                this.res.sub();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

