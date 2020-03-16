//package algorithm.list;
//
///**
// * 基于数组实现LRU
// * @author Richard_yyf
// * @version 1.0 2019/11/22
// */
//public class LRUCacheBaseArray<T> {
//
//    /**
//     * 默认链表容量
//     */
//    private static final Integer DEFAULT_CAPACITY = 10;
//
//    private int capacity;
//
//    private int count;
//
//    private T[] data;
//
//    // 插入数据，如果数据已存在，则放到数组头部，如果不存在，如果缓存已满，先删除尾节点，在添加到头部
//
//    public void add(T ele) {
//        // 查找这个数据是否存在，如果存在返回它的前一个节点
//        Integer index = find(ele);
//
//        if (index != null) {
//            // 删除
//            delete(index);
//            // 插入链表头部
//            insertToHead(ele);
//        } else {
//            // 当前数据不存在
//            if (length + 1 > capacity) {
//                // 缓存已满，删除尾节点
//                deleteLastEle();
//            }
//            insertToHead(ele);
//        }
//
//    }
//
//    // 删除指定位置结点
//
//    private void delete(Integer index) {
//        // 删除指定位置
//        data[index] = null;
//        // 把前面的结点移动到前面
//
//    }
//}
