package person.davino.basic.reflect;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        Hello hello = new HelloProxy(new HelloService()).getProxy();
        hello.say();
    }
}
