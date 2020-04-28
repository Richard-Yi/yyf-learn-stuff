package oop.klass.model;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

/**
 * @author Richard_Yi
 * @version 1.0 2020/4/28
 */
public class TestMain {

    public static void main(String[] args) {

        // 空对象内存占用
//        System.out.println(ClassLayout.parseInstance(new NullObject()).toPrintable());

        // 对象内存占用
        System.out.println(ClassLayout.parseInstance(new NotNullObj()).toPrintable());
        // 输出对象相关的所有内存占用
        System.out.println("====================");
        System.out.println(GraphLayout.parseInstance(new NotNullObj()).toPrintable());
        // 输出内存占用统计
        System.out.println("====================");
        System.out.println(GraphLayout.parseInstance(new NotNullObj()).toFootprint());
    }
}
