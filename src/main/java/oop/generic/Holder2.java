package oop.generic;

/**
 * 想要在java5 之前实现可重用性的容器类
 * @author Richard_yyf
 * @version 1.0 2019/8/29
 */
public class Holder2 {

    private Object a;

    public Holder2(Object a) {
        this.a = a;
    }

    public Object getA() {
        return a;
    }

    public void setA(Object a) {
        this.a = a;
    }

    public static void main(String[] args) {
        Holder2 h2 = new Holder2(new AutoMobile());
        AutoMobile a = (AutoMobile) h2.getA();
        h2.setA("Not an AutoMobile");
        String s = (String) h2.getA();
        h2.setA(1);
        Integer x = (Integer) h2.getA();
    }

    private <E,T> T testMethod(E e, T t) {
        return t;
    }
}
