package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * a demo to simulate threadLocal OOM situation
 * JVM -Xmx256m
 *
 * @author Richard_yyf
 * @version 1.0 2019/7/4
 */
public class ThreadLocalOOMDemo {

    private static final int THREAD_LOOP_SIZE = 500;

    private static final int SIZE = 10000;

    private static ThreadLocal<List<User>> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        ThreadLocalOOMDemo demo = new ThreadLocalOOMDemo();
        demo.testCase();
    }

    private void testCase() {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_LOOP_SIZE);

        for (int i = 0; i < THREAD_LOOP_SIZE; i++) {
            executorService.execute(() -> {
                threadLocal.set(generateBigUserList());
                System.out.println(Thread.currentThread().getName());
                // don't execute remove() here
            });

            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private List<User> generateBigUserList() {
        List<User> list = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; i ++) {
            list.add(new User("user", "male", "123", 18));
        }
        return list;
    }

    private class User {
        String name;
        String sex;
        String psw;
        int age;

        public User(String name, String sex, String psw, int age) {
            this.name = name;
            this.sex = sex;
            this.psw = psw;
            this.age = age;
        }
    }
}
