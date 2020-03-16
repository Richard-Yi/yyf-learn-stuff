package oop;

/**
 * @author Richard_yyf
 * @version 1.0 2019/6/16
 */
public class OverloadExample {

    public void methodForOverload() {
        System.out.println("无参方法");
    }

//    public void methodForOverload(long param) {
//        System.out.println("long 参数 方法");
//    }

    public void methodForOverload(int param) {
        System.out.println("int 参数 方法");
    }

//    public void methodForOverload(Object param) {
//        System.out.println("super class 参数 方法");
//    }

    public static void main(String[] args) {
        OverloadExample e = new OverloadExample();

        e.methodForOverload(new Integer(3));
    }
}
