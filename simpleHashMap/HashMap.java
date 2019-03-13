public class HashMap {
    Node store[] = new Node[10];
    private int hash(Object obj) {
        int h = obj.hashCode();
        if(0 > h) {
            h *= -1;
        }
        return h%10;
    }
    // 设置方法
    public void put(Object key, Object value) {
        // 确定该对象放入数组中的位置
        int position = this.hash(key);
        if (null == this.store[position]) {
            this.store[position] = new Node(key, value, null);
            return;
        }
        // 放入指定位置
        Node current = this.store[position];
        while(true) {
            if(current.key.equals(key)) {
                current.value = value;
                break;
            }
            if(null == current.next) {
                current.next = new Node(key, value, null);
            }
            current = current.next;
        }
    }
    // 获取方法
    public Object get(Object key) {
        // 确定该对象放置的数组位置
        int position = this.hash(key);
        if (null == this.store[position]) {
            return null;
        }
        Node current = this.store[position];
        while(true) {
            if(current.key.equals(key)) {
                return current.value;
            }
            if(null == current.next) {
                return null;
            }
            current = current.next;
        }
    }
}
