package oop.generic;

import java.lang.reflect.Field;

/**
 * 类型擦除 相关类
 *
 * @author Richard_yyf
 * @version 1.0 2019/8/29
 */
public class EraseHolder<T> {

    T data;

    public EraseHolder(T data) {
        this.data = data;
    }

    public static void main(String[] args) {
        EraseHolder<String> holder = new EraseHolder<>("hello");
        Class clazz = holder.getClass();
        System.out.println("erasure class is:" + clazz.getName());

        Field[] fs = clazz.getDeclaredFields();
        for ( Field f:fs) {
            // 那我们可不可以说，泛型类被类型擦除后，相应的类型就被替换成 Object 类型呢？
            System.out.println("Field name "+f.getName()+" type:"+f.getType().getName());
        }

        EraseHolder2<String> holder2 = new EraseHolder2<>("hello");
        clazz = holder2.getClass();
        fs = clazz.getDeclaredFields();
        for ( Field f:fs) {
            // 那我们可不可以说，泛型类被类型擦除后，相应的类型就被替换成 Object 类型呢？
            System.out.println("Field name "+f.getName()+" type:"+f.getType().getName());
        }
    }

    static class EraseHolder2<T extends String> {
        T data;

        public EraseHolder2(T data) {
            this.data = data;
        }
    }
}
