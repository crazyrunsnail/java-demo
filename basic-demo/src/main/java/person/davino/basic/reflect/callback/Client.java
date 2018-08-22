package person.davino.basic.reflect.callback;

/**
 * 这个示例有展示了, Proxy不能强制转型, 因为proxy不能直接转向实现类, 以达到保护的代码的目的
 */
public class Client {

    public static void main(String[] args) {
        Service service = new DefaultServiceImpl();
        ServiceCallback callback = new ServiceConsumer(new DefaultServiceImpl());
//        service.serviceMethod(callback);
//        ServiceConsumer serviceConsumer  = (ServiceConsumer) callback;
//        serviceConsumer.someMethod();
        ServiceCallback proxy = GenericProxyFactory.getProxy(ServiceCallback.class, callback);
       proxy.doCallback();

    }
}
