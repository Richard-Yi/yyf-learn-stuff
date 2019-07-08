package collections;

import java.util.HashSet;

/**
 * @author Richard_yyf
 * @version 1.0 2019/7/8
 */
public class HashSetDemo {

    public static void main(String[] args) {
        HashSet<Object> hashSet = new HashSet<>();
        hashSet.add(new A());
        hashSet.add(new A());
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