package person.davino.basic.jvm.classload.classinit;

public class SuperClass {
    static {
        System.out.println("Super class!!!!");
    }

    public static int value = 123;

    public static final int CONSTANT = 123;

}
