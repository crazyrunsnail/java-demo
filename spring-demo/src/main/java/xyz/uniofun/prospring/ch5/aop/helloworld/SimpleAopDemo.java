package xyz.uniofun.prospring.ch5.aop.helloworld;

import org.springframework.aop.framework.ProxyFactory;

public class SimpleAopDemo {

    public static void main(String[] args) {
        Agent target = new Agent();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new AgentDecorator());
        proxyFactory.setTarget(target);

        Agent proxy = (Agent)proxyFactory.getProxy();

        System.out.println("Speak...");
        proxy.speak();
    }
}
