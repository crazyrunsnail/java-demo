package person.davino.basic.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HelloProxy implements InvocationHandler{
    Hello target;

    public HelloProxy(Hello target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("====before====");
        Object result = method.invoke(target, args);
        System.out.println("=====after=====");
        return result;
    }

    public Hello getProxy() {
        return (Hello)Proxy.newProxyInstance(this.getClass().getClassLoader(),
                HelloService.class.getInterfaces(), this);
    }
}
