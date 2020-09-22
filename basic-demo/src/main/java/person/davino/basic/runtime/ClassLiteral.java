package person.davino.basic.runtime;

/**
 *
 */
public class ClassLiteral {
    private static final Class klass = int.class;
    private static final Class klass1 = Integer.TYPE;

    static {
        System.out.println("Print when class Load!");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(klass == klass1); // true
        System.out.println(ClassLiteral.TestClass.class);
        // 加载class
        Class.forName("person.davino.basic.runtime.ClassLiteral$TestClass");
    }

    private static class TestClass {
        static {
            System.out.println("Print when inner class init.");
        }
    }
}

