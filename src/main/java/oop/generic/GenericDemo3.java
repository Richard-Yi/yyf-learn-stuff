package oop.generic;

import java.util.Collection;

/**
 *
 * @author Richard_yyf
 * @version 1.0 2019/8/29
 */
public class GenericDemo3 {

    /**
     * 测试 无限定通配符 <?>
     * @param collection c
     */
    public void testUnBoundedGeneric(Collection<?> collection) {
//        collection.add(123);
//        collection.add("123");
//        collection.add(new Object());

        // 你只能调用 Collection 中与类型无关的方法。（看源码
        collection.iterator().next();
        collection.clear();
        collection.size();

    }

    class Food {}

    class Fruit extends Food {}

    class Apple extends Fruit {}

    class Banana extends Fruit {}

    class Plate<T> {
        private T item;

        public Plate(T item) {
            this.item = item;
        }

        public T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }
    }

    /**
     * 测试 上限 通配符 <? extends T>
     * 如果你需要一个只读容器，用它来produce T，那么使用<? extends T> 。
     */
    public void testUpperBoundedGeneric() {
       Plate<? extends Fruit> p = new Plate<>(new Apple());

       // 不能存入任何元素
//        p.setItem(new Fruit());
//        p.setItem(new Apple());

        // 编译器只知道容器内是Fruit或是它的派生类，但是具体什么类型不知道？ 可能是Fruit、Apple、Banana

        // 读出来的元素需要是 Fruit或者Fruit的基类
        Fruit fruit = p.getItem();
        Food food = p.getItem();
//        Apple apple = p.getItem();
    }


    /**
     * 测试 下限 通配符 <? super T>
     * 如果你需要一个只写容器类，用它来consume T，那么使用<? super T>。
     */
    public void testLowerBoundedBoundedGeneric() {
//        Plate<? super Fruit> p = new Plate<>(new Food());
        Plate<? super Fruit> p = new Plate<>(new Fruit());

        // 存入元素正常
        p.setItem(new Fruit());
        p.setItem(new Apple());

        // 读取出来的东西，只能放在Object中
//        Apple apple = p.getItem();
        Object o = p.getItem();


        // 因为下界规定了元素的最小粒度的下限，实际上是放松了容器元素的类型控制。
        // 既然元素是Fruit的基类，往里面存比Fruit粒度小的类都可以。
        // 但是往外读取的话就费劲了，只有所有类的基类Object可以装下。但这样一来元素类型信息就都丢失了。
    }

    // 如果需要同时读取以及写入，那么我们就不能使用通配符了。
}
