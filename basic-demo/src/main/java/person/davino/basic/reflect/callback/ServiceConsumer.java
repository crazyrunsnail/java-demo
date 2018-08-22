package person.davino.basic.reflect.callback;

public class ServiceConsumer implements ServiceCallback {

    private Service service;

    public ServiceConsumer(Service service) {
        this.service = service;
    }

    @Override
    public void doCallback() {
        System.out.println("do callback;");
    }

    public void someMethod() {
        service.serviceMethod(this);
    }
}
