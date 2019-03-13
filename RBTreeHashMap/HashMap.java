import java.util.TreeMap;
import java.util.Iterator;

public class HashMap {
    private int defaultVal;
    private double scaleFactor;
    private Node store[];
    private int toBRTreeVal = 4;
    public HashMap() {
        this.defaultVal = 16;
        this.scaleFactor = 0.75;
        this.store = new Node[defaultVal];
    }
    public HashMap(int defaultVal, float scaleFactor) {
        this.defaultVal = defaultVal;
        this.scaleFactor = scaleFactor;
        this.store = new Node[defaultVal];
    }
    private int hash(Object obj) {
        int h = obj.hashCode();
        if(0 > h) {
            h *= -1;
        }
        return h%this.defaultVal;
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
        int linkLen = 0;
        while(true) {
            linkLen++;
            if(current.key.equals(key)) {
                current.value = value;
                break;
            }
            if(null == current.next) {
                current.next = new Node(key, value, null);
                break;
            }
            current = current.next;
        }
        if (this.toBRTreeVal < linkLen) {
            TreeMap treeMap = new TreeMap();
            while(true) {
                treeMap.put(current.key, current.value);
                if(null == current.next) {
                    break;
                }
                current = current.next;
            }
            this.store[position].key = "BRTree";
            this.store[position].value = treeMap;
        }
        // 获得数组非空值
        int notNullNum = this.defaultVal;
        for(int i = 0; i < this.defaultVal; i++) {
            if (null == this.store[i]) {
                notNullNum--;
            }
        }
        // 达到阀门值，自动触发扩容
        if ( notNullNum > (int)(this.defaultVal * this.scaleFactor) ) {
            this.resize(2);
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
            if("RBTree" == current.key && current.value instanceof TreeMap) {
                TreeMap currentTreeMap = (TreeMap)current.value;
                return currentTreeMap.get(key);
            }
            if(current.key.equals(key)) {
                return current.value;
            }
            if(null == current.next) {
                return null;
            }
            current = current.next;
        }
        
    }
    // 扩容方法
    public void resize(int multiplier) {
        int oldDefaultVal = this.defaultVal;
        this.defaultVal *= multiplier;
        Node newStore[] = new Node[this.defaultVal];
        for(int i = 0; i < oldDefaultVal; i++) {
            if(null != this.store[i]) {
                Node current = this.store[i];
                if (current.key == "RBTree" && current.value instanceof TreeMap) {
                    TreeMap currentTreeMap = (TreeMap)current.value;
                    newStore[this.hash(currentTreeMap.firstKey())] = current;
                } else if (current instanceof Node) {
                    newStore[this.hash(current.key)] = current;
                }
            }
        }
        this.store= newStore;
    }
    // 打印转化状况
    public void testTrans() {
        System.out.printf("当前数组大小为%d\n", this.defaultVal);
        for(int i = 0; i < this.defaultVal; i++) {
            System.out.printf("第%d位：", i);
            Node current = this.store[i];
            if (null != current && current.key == "RBTree" && current.value instanceof TreeMap) {
                TreeMap currentTreeMap = (TreeMap)current.value;
                Iterator it = currentTreeMap.keySet().iterator();
                while (it.hasNext()) {
                    System.out.printf("BRTree:[%s]=%s", it.next(), currentTreeMap.get(it.next()));
                }
            } else if (current instanceof Node) {
                int j = 0;
                while(true) {
                    if(null != current) {
                        System.out.printf("link:%d->[%s]=%s ", j, current.key, current.value);
                    }else {
                        System.out.printf("该位无值");
                        break;
                    }
                    if(null == current.next) {
                        break;
                    }
                    current = current.next;
                    j++;
                }
            } else {
                System.out.printf("无");
            }
            System.out.println();
        }
        System.out.println();
    }
}
