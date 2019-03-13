public class Main {
    public static void main(String[] args) {
        HashMap myHM = new HashMap();
        // 测试基础功能 
        myHM.put("hello", "world");
        myHM.put("foo", "bar");
        myHM.put("hello", "Dawn");
        System.out.println(myHM.get("hello")); 
        System.out.println(myHM.get("foo")); 
        // 查询不存在的数据 
        System.out.println(myHM.get("some")); 
    }
}
