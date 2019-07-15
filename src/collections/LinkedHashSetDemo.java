package collections;

import java.util.LinkedHashSet;

/**
 * 内部用双向链表维护元素的插入顺序
 * 其他特性和HashSet一样
 * @author Richard_yyf
 * @version 1.0 2019/7/8
 */
public class LinkedHashSetDemo {

    public static void main(String[] args) {
        LinkedHashSet<Integer> nums = new LinkedHashSet<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        nums.add(5);
        nums.add(6);
        System.out.println(nums);

        // remove 5
        nums.remove(5);
        // re-add 5
        nums.add(5);

        System.out.println(nums);
    }
}
