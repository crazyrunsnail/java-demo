package person.davino.basic.runtime;

/**
 * 继承后的动态绑定
 * 即使使用的强转成是父类，当赋值是子类的，还是调用子类的实现方式
 */
public class ImplementClassTest {
    static class A {
        public void a() {
            System.out.println("A.a()");
        }
    }

    static class B extends A {
        @Override
        public void a() {
            System.out.println("B.a()");
        }
    }

    public static void main(String[] args) {
        A a = new B();
        a.a(); // B.a()
        if (a instanceof A) {
            ((A) a).a(); // B.a()
        }

        A aa = new A();
        aa.a();
    }
}
