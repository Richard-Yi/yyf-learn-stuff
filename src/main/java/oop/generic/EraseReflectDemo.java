package oop.generic;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard_yyf
 * @version 1.0 2019/8/29
 */
public class EraseReflectDemo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(23);
        // can't add here
        // 因为泛型的限制 boolean add(E e);
//        list.add("123");

        // 利用反射可以绕过编译器去调用add方法
        // 又因为类型擦除时 boolean add(E e); 等同于 boolean add(Object e);

        try {
            Method method = list.getClass().getDeclaredMethod("add", Object.class);

            method.invoke(list, "test");
            method.invoke(list, 42.9f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Object o : list) {
            System.out.println(o);
        }


    }
}
