package person.davino.dp.proxy.jdk;

import java.lang.reflect.Proxy;

public class Client {

    public static void main(String[] args) {
        PersonBean personBean = new PersonBeanImpl();
        PersonBean proxy = (PersonBean)Proxy.newProxyInstance(personBean.getClass().getClassLoader(),
                personBean.getClass().getInterfaces(),
                new OwnInvocationHandler(personBean));
        proxy.setName("a");
        System.out.println(proxy.getName());
        System.out.println(personBean.getClass().getCanonicalName());
        System.out.println(proxy.getClass().getCanonicalName());
    }
}
