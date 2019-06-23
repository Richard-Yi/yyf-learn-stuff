package oop;

import java.text.SimpleDateFormat;

/**
 * @author Richard_yyf
 * @version 1.0 2019/6/16
 */
public class LongIntegerCache {

    public static void main(String[] args) {

        Integer a = 10001;
        Integer b = Integer.valueOf(10001);
        String a1 = "111";
        String b2 = "111";
        System.out.println(a1==b2);
//        Integer a = 111;
//        Integer b = 111;
        System.out.println(a);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Integer a==b : " + String.valueOf(a==b));
    }
}
