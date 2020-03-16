package algorithm.list;

/**
 * @author Richard_yyf
 * @version 1.0 2019/11/22
 */
public class Bootstrap {

    public static void main(String[] args) {
        LRUCacheBaseLinkedList<String> cache = new LRUCacheBaseLinkedList<>(3);
        cache.add("1");
        cache.add("2");
        // should print 2
        System.out.println(cache.findIndex("1"));
        cache.add("1");
        // should print 1
        System.out.println(cache.findIndex("1"));
    }
}
