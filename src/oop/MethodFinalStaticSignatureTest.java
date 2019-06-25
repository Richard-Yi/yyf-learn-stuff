package oop;

/**
 * @author Richard_yyf
 * @version 1.0 2019/6/25
 */
public class MethodFinalStaticSignatureTest {

    public static void main(String[] args) {

        // 静态方法的继承没有任何意义，因为没有多态性
        // 本身Override的意义就在覆盖父类的同名方法，但是static修饰之后导致，这个还是可以被访问
        // 当然 使用 final 声明了之后，子类就不能Override这个方法了
        // 所以编译器会提示
        // <p> When a static method is overridden in a subclass
        // it can still be accessed via the super class,
        // making a final declaration not very necessary.
        // Declaring a static method final does prevent subclasses from defining a static method with the same signature.</P>
        Father.speak();
        Son.speak();
    }
}

class Father {
    public final static void speak() {
        System.out.println("I'm a father");
    }
}

class Son extends Father {
//    public static void speak() {
//        System.out.println("I'm a son");
//    }
}