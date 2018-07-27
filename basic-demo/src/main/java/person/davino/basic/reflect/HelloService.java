package person.davino.basic.reflect;

public class HelloService implements Hello{
    @Override
    public void say() {
        System.out.println("Say hello!");
    }
}
