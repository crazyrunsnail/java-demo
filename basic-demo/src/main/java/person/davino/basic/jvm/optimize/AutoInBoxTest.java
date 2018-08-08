package person.davino.basic.jvm.optimize;

public class AutoInBoxTest {

    public static void main(String[] args) {
        Integer a = 100;
        Integer b = 200;
        Integer c = 300;
        Integer d = 300;
        Integer e = 321;
        Integer f = 321;
        Long g = 321L;
        System.out.println(c == d); // false 对比地址
        System.out.println(e == f); // false 对比地址
        System.out.println(c == (a + b)); // 自动拆箱 300 = 100 + 200
        System.out.println(c.equals(a + b)); // true
        System.out.println(g == (a + b)); // false
        System.out.println(g.equals(a + b));  // 类似不同所以 equals false
        System.out.println(321L == 321);  // true

    }
}
