package person.davino.basic.jvm.classload.classinit;

public class SubClass extends SuperClass{
    static {
        System.out.println("Sub class!");
    }
}
