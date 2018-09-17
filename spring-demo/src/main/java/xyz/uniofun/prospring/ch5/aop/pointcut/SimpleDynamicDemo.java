package xyz.uniofun.prospring.ch5.aop.pointcut;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class SimpleDynamicDemo {
    public static void main(String[] args) {
        SimpleBean target = new SimpleBean();

        Advisor advisor = new DefaultPointcutAdvisor(new SimpleDynamicPointcut(), new SimpleAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);

        SimpleBean proxy = (SimpleBean) pf.getProxy();

        proxy.foo(1);
        proxy.foo(10);
        proxy.foo(100);
        proxy.bar();
        proxy.bar();
        proxy.bar();
    }

    public static class SimpleAdvice implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {
            System.out.println(">> Invoke foo");
            Object returnVal = methodInvocation.proceed();
            System.out.println(">> Done");
            return returnVal;
        }
    }
}
