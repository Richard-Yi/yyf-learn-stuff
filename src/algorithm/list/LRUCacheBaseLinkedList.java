package algorithm.list;

/**
 * 单链表实现LRU
 *
 * @author Richard_yyf
 * @version 1.0 2019/11/22
 */
public class LRUCacheBaseLinkedList<T> {

    /**
     * 默认链表容量
     */
    private static final Integer DEFAULT_CAPACITY = 10;

    /**
     * 头部节点
     */
    private Node<T> headNode;

    /**
     * 链表长度
     */
    private Integer length;

    /**
     * 缓存容量
     */
    private Integer capacity;

    public LRUCacheBaseLinkedList() {
        // 这里有学问的，放一个空的头节点，当做哨兵，不计入链表长度
        this.length = DEFAULT_CAPACITY;
        this.length = 0;
        this.headNode = new Node<>();
    }

    public LRUCacheBaseLinkedList(Integer capacity) {
        this.capacity = capacity;
        this.length = 0;
        this.headNode = new Node<>();
    }

    // 插入数据，如果数据已存在，则放到链表头部，如果不存在产生新节点，如果缓存已满，先删除尾节点

    public void add(T data) {
        // 查找这个数据是否存在，如果存在返回它的前一个节点
        Node<T> preNode = findPreNode(data);

        if (preNode != null) {
            // 删除当前结点位置
            Node<T> node = preNode.next;
            preNode.next = node.next;
            length--;
            // 插入链表头部
            insertToHead(node);
        } else {
            // 当前数据不存在
            Node<T> node = new Node<>(data);
            if (length + 1 > capacity) {
                // 缓存已满，删除尾节点
                deleteLastNode();
            }
            insertToHead(node);
        }

    }

    // 添加这个方法用以验证LRU

    public Integer findIndex(T data) {
        Node<T> node = headNode;

        int index = 0;
        while (node.next != null) {
            if (data.equals(node.next.element)) {
                return ++index;
            }
            index++;
            node = node.next;
        }
        return null;
    }

    private Node<T> findPreNode(T data) {
        Node<T> node = headNode;
        if (node.next == null) {
            // 缓存为空
            return null;
        }

        while (node.next.next != null) {
            if (data.equals(node.next.element)) {
                return node;
            }
            node = node.next;
        }

        return null;
    }

    private void deleteLastNode() {
        Node<T> node = headNode;
        // 空链表
        if (node.next == null) {
            return;
        }
        while (node.next.next != null) {
            node = node.next;
        }
        // 删除尾结点
        node.next = null;
    }

    private void insertToHead(Node<T> node) {
        node.next = headNode.next;
        headNode.next = node;
        length++;
    }

    class Node<T> {
        T element;
        Node<T> next;

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }

        public Node(T element) {
            this.element = element;
        }

        public Node() {
            this.next = null;
        }
    }
}
