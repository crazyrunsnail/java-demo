package xyz.uniofun.prospring.ch5.aop.pointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import xyz.uniofun.prospring.ch5.aop.adivce.Guitarist;
import xyz.uniofun.prospring.ch5.aop.adivce.before.SimpleBeforeAdivce;

import java.lang.reflect.Method;

public class SimpleStaticPointcut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> aClass) {
        return true;
    }

    @Override
    public ClassFilter getClassFilter() {
        return aClass -> aClass == Guitarist.class;
    }

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(new SimpleStaticPointcut());
        advisor.setAdvice(new SimpleBeforeAdivce());

        proxyFactory.addAdvisor(advisor);
        proxyFactory.setTarget(new Guitarist());

        Guitarist guitarist = (Guitarist) proxyFactory.getProxy();
        guitarist.sing();
    }
}
