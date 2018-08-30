package xyz.uniofun.prospring.ch5.aop.adivce.before;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import xyz.uniofun.prospring.ch5.aop.adivce.Guitarist;
import xyz.uniofun.prospring.ch5.aop.adivce.SimpleAfterReturningAdvice;
import xyz.uniofun.prospring.ch5.aop.adivce.around.ProfilingInterceptor;

import java.lang.reflect.Method;

public class SimpleBeforeAdivce implements MethodBeforeAdvice {

    public static void main(String[] args) {
        Guitarist guitarist = new Guitarist();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new ProfilingInterceptor());
        proxyFactory.addAdvice(new SimpleBeforeAdivce2());
        proxyFactory.addAdvice(new SimpleBeforeAdivce());
        proxyFactory.addAdvice(new SimpleAfterReturningAdvice());
        proxyFactory.setTarget(guitarist);
        Guitarist proxy = (Guitarist)proxyFactory.getProxy();
        proxy.sing();
    }

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("Before '" + method.getName() + "', tune guitar");
    }

    public static class SimpleBeforeAdivce2 implements MethodBeforeAdvice {

        @Override
        public void before(Method method, Object[] objects, Object o) throws Throwable {
            System.out.println("SimpleBeforeAdivce2 ....");
        }
    }
}
