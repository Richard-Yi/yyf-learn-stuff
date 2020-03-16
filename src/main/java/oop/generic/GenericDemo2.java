package oop.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard_yyf
 * @version 1.0 2019/8/29
 */
public class GenericDemo2 {

    class Base{}

    class Sub extends Base{}

    public void test() {
        // 继承关系
        Sub sub = new Sub();
        Base base = sub;
        List<Sub> lsub = new ArrayList<>();
        // 编译器是不会让下面这行代码通过的，
        // 因为 Fruit 是 Food 的子类，不代表 List<Fruit>和 List<Food>有继承关系。
//        List<Food> lbase = lsub;
    }
}
