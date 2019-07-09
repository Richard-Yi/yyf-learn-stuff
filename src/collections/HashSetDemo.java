package collections;

import java.util.HashSet;

/**
 * @author Richard_yyf
 * @version 1.0 2019/7/8
 */
public class HashSetDemo {

    public static void main(String[] args) {
        HashSet<Object> hashSet = new HashSet<>();
        // class A 的 equal() 总是true hashCode() 方法没有重写
        // 会导致HashSet把两个对象保存在Hash表的不同位置，从而使两个equals()=true的对象都添加成功
        hashSet.add(new A());
        hashSet.add(new A());
        // class B 的 hashCode() 总是1
        // equal() 方法没有重写
        // 更麻烦的情况，hashCode()方法返回相同，HashSet会尝试保存这两个对象到同一个位置，
        // 但是又不行，所以这个位置实际上会使用链式结构存储多个对象，这样子会导致HashSet的性能下降
        hashSet.add(new B());
        hashSet.add(new B());
        hashSet.add(new C());
        hashSet.add(new C());
        System.out.println(hashSet);
    }
}

/**
 * class A 的 equal() 总是true
 * hashCode() 方法没有重写
 */
class A {
    @Override
    public boolean equals(Object obj) {
        return true;
    }


}


/**
 * class B 的 hashCode() 总是1
 * equal() 方法没有重写
 */
class B {
    @Override
    public int hashCode() {
        return 1;
    }
}

/**
 * class C 的 hashCode() 总是1
 * equal() 方法没有重写
 */
class C {

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}