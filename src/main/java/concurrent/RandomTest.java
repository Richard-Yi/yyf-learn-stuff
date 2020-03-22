package main.java.concurrent;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Richard_yyf
 * @version 1.0 2020/3/22
 */
public class RandomTest {

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(5));
        }
        System.out.println("==========");
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        for (int i = 0; i < 10; i++) {
            System.out.println(threadLocalRandom.nextInt(5));
        }
    }

}


