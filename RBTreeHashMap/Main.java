public class Main {
    public static void main(String[] args) {
        HashMap myHM = new HashMap();
        // 测试基础功能 
        myHM.put("hello", "world");
        myHM.put("foo", "bar");
        System.out.println(myHM.get("hello")); 
        System.out.println(myHM.get("foo")); 
        // 查询不存在的数据 
        System.out.println(myHM.get("some")); 
        
        // 添加足量值触发红黑树
        for(int i = 0; i < 300; i++) {
            String key = "key"+i;
            String value = "value"+i;
            myHM.put(key, value); 
        }
        // 打印转换情况
        myHM.testTrans();
    }
}
