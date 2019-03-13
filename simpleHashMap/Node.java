public class Node {
    // 对象
    public Object key;
    public Object value;
    // 下一节点
    public Node next;
    public Node(Object key, Object value, Node next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}

