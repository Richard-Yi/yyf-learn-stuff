package collections;

import java.util.Arrays;
import java.util.List;

/**
 * Array.asList(T... a) 的坑
 *
 * @author Richard_yyf
 * @version 1.0 2019/7/15
 */
public class ArrayAsListDemo {

    public static void main(String[] args) {
        String[] stringArray = new String[3];
        stringArray[0] = "one";
        stringArray[1] = "two";
        stringArray[2] = "three";

        List<String> stringList = Arrays.asList(stringArray);
        // 修改 转换后的集合
        stringList.set(0, "oneList");
        // 修改成功
        System.out.println(stringArray[0]);

        // 编译会通过
        // add/remove/clear 方法会抛出 UnsupportedOperationException。
        stringList.add("four");
        stringList.remove(2);
        stringList.clear();

    }
}
