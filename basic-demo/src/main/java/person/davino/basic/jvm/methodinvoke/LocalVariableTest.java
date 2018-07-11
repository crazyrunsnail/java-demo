package person.davino.basic.jvm.methodinvoke;

public class LocalVariableTest {

    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        System.gc();
    }
}
