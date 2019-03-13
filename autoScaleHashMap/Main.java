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
        
        // 添加足够的值触发第一次扩容
        for(int i = 0; i < 100; i++) {
            String key = "key"+i;
            String value = "value"+i;
            myHM.put(key, value); 
        }
        // 打印扩容后的数据存储情况
        myHM.testResize();
        // 添加足够的值触发第二次扩容
        for(int i = 100; i < 150; i++) {
            String key = "key"+i;
            String value = "value"+i;
            myHM.put(key, value); 
        }
        // 打印扩容后的数据存储情况
        myHM.testResize();
    }
}
