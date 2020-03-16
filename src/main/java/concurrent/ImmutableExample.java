package concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 保证线程安全的一种方式 - 保证对象不可变
 * <>
 *     1. final 修饰的基本数据类型
 *     2. String
 *     3. 枚举类型
 *     4. Number 部分类型 如 Long 和 Double 等数值包装类型，BigInteger 和 BigDecimal 等大数据类型。
 *     但同为 Number 的原子类 AtomicInteger 和 AtomicLong 则是可变的。
 *
 *     对于集合类型，可以使用 Collections.unmodifiableXXX() 方法来获取一个不可变的集合。
 * </>
 * @author Richard_yyf
 * @version 1.0 2019/7/3
 */
public class ImmutableExample {

    private static Long longVal = 1L;
    private static Double doubleVal = 123D;
//    private static List<Object> testList = new ArrayList<>(10);

    public static void main(String[] args) {
//        testCase1();
        testCase2();
    }

    private static void testCase1() {
        System.out.println(longVal);
        System.out.println(System.identityHashCode(longVal));
        // 这里是做了拆箱装箱操作，并不能说明Long这种类型是可变的
        longVal++;
//        testList add("123");
        System.out.println(longVal);
        System.out.println(System.identityHashCode(longVal));
    }

    private static void testCase2() {
        Map<String, Integer> map = new HashMap<>(3);
        Map<String, Integer> unmodifiableMap = Collections.unmodifiableMap(map);
        // ide 会在这里提示
        unmodifiableMap.put("a", 1);
    }
}
