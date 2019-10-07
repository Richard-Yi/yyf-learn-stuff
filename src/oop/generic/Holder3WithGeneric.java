package oop.generic;

/**
 * 通过泛型来实现可重用性
 * 泛型的主要目的是指定容器要持有什么类型的对象
 * 而且由编译器来保证类型的正确性
 *
 * @author Richard_yyf
 * @version 1.0 2019/8/29
 */
public class Holder3WithGeneric<T> {

    private T a;

    public Holder3WithGeneric(T a) {
        this.a = a;
    }

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    public static void main(String[] args) {
        Holder3WithGeneric<AutoMobile> h3 = new Holder3WithGeneric<>(new AutoMobile());
        // No class cast needed
        AutoMobile a = h3.getA();
    }
}
