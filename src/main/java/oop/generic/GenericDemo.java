package oop.generic;

/**
 * 泛型类与泛型方法的共存现象
 * @author Richard_yyf
 * @version 1.0 2019/8/29
 */
public class GenericDemo<T> {

    public  void testMethod(T t){
        System.out.println(t.getClass().getName());
    }
    public  <T> T testMethod1(T t){
        return t;
    }

    public static void main(String[] args) {
        GenericDemo<String> t = new GenericDemo<>();
        t.testMethod("generic");
        Integer integer = 1;
        Integer i = t.testMethod1(integer);

    }
}
