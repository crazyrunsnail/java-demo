package person.davino.basic.reflect.callback;

public class DefaultServiceImpl implements Service{
    private static int count = 0;
    public final int id = count++;

    @Override
    public void serviceMethod(ServiceCallback callback) {
        System.out.println("DefaultServiceImpl[ " + id + "].serviceMethod");
        System.out.println("DefaultServiceImpl[ " + id + "] doing callback");
        callback.doCallback();
        System.out.println("DefaultServiceImpl[ " + id + "] finished callback");
    }
}
