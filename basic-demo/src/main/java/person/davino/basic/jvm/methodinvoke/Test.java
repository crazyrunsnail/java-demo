package person.davino.basic.jvm.methodinvoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Test {
    class GrandFather {
        void thinking() {
            System.out.println("i am grandfather");
        }
    }

    class Father extends GrandFather {
        @Override
        void thinking() {
            System.out.println("i am father");
        }
    }

    class Son extends Father {
        @Override
        void thinking() {
            MethodType mt = MethodType.methodType(void.class);
            try {
                MethodHandle thinking = MethodHandles.lookup().
                        findSpecial(GrandFather.class, "thinking", mt, getClass());
                thinking.invoke(this);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        (new Test().new Son()).thinking();
    }
}
