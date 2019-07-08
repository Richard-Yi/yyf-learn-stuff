package oop;

/**
 * The constructor Confusing(Object)accepts any parameter passed to Confusing(double[]),
 * so Confusing(Object) is less specific.
 * (Every double array is an Object, but not every Object is a double array.)
 * The most specific constructor is therefore Confusing(double[]), which explains the program's output.
 *
 * https://stackoverflow.com/questions/1572322/overloaded-method-selection-based-on-the-parameters-real-type
 *
 * @author Richard_yyf
 * @version 1.0 2019/7/8
 */
public class MethodOverLoadConfusingDemo {

    public static void confuse(Object o) {
        System.out.println("Object");
    }
    public static void confuse(double[] dArray) {
        System.out.println("double array");
    }
//    public static void confuse(int[] dArray) {
//        System.out.println("int array");
//    }
    public static void confuse(int dArray) {
        System.out.println("int array");
    }
    public static void main(String args[]) {
        confuse(null);
    }
}
