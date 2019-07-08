package collections;

import java.util.TreeSet;

/**
 * @author Richard_yyf
 * @version 1.0 2019/7/8
 */
public class TreeSetDemo {

    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        System.out.println(set.add(1));
        //  this time would print false here
        System.out.println(set.add(1));
        System.out.println(set.add(null));

        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        System.out.println(set.lower(3));
    }
}
