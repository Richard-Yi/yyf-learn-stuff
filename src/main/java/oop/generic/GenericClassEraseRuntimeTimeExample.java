package oop.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard_yyf
 * @version 1.0 2019/8/29
 */
public class GenericClassEraseRuntimeTimeExample {

    public static void main(String[] args) {
        List<String> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        List<Object> l3 = new ArrayList<>();
//        l3 = l1;  // compilation error incompatible types
        System.out.println(l1.getClass() == l2.getClass());
    }
}
