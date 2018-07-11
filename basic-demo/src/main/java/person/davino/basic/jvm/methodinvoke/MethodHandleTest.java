package person.davino.basic.jvm.methodinvoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleTest {
    static class ClassA {
        public void println(String s) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws Throwable {
        Object object = System.currentTimeMillis() % 2 == 0 ? System.out: new ClassA();
        System.out.println("Object.class: " + object.getClass());
        getPrinlnMH(object).invokeExact("icyfengx");

    }

    private static MethodHandle getPrinlnMH(Object receiver) throws Throwable {
        MethodType methodType = MethodType.methodType(void.class, String.class);
        return MethodHandles.lookup().findVirtual(receiver.getClass(), "println", methodType).bindTo(receiver);
    }
}
