package collections;

import java.util.HashSet;
import java.util.Iterator;

/**
 * 当程序把可变对象添加到HashSet集合中之后，尽量不要修改对象中参与equal()和hashCode()运算的参数！！
 *
 * @author Richard_yyf
 * @version 1.0 2019/7/8
 */
public class HashSetDemo2 {

    public static void main(String[] args) {
        HashSet<R> set = new HashSet<>();
        set.add(new R(5));
        set.add(new R(-3));
        set.add(new R(9));
        set.add(new R(-2));

        // print set
        System.out.println(set);

        // get the first element
        Iterator<R> iterator = set.iterator();
        R first = iterator.next();
        // set value to the first ele
        // -3  这个值是和集合中其中一个元素重合了
        first.count = -3;
        // 观察现象，出现重复元素
        System.out.println(set);

        // 删除 -3 的R 对象
        // 计算 hashCode值，然后计算出对象保存位置，把内存中对象和入参的R对象进行equal 比较，相等则删除
        set.remove(new R(-3));
        // 观察
        System.out.println(set);

        System.out.println("if set contains -3 ele : " + set.contains(new R(-3)));
        System.out.println("if set contains -2 ele : " + set.contains(new R(-2)));

    }
}

class R {
    int count;

    public R(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "R{" +
                "count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        R r = (R) o;
        return count == r.count;
    }

    @Override
    public int hashCode() {
        return this.count;
    }
}