package xyz.uniofun.prospring.ch3.di.methodreplace;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class FormatMethodReplacer implements MethodReplacer {
    @Override
    public Object reimplement(Object object, Method method, Object[] args) throws Throwable {
        if (isFormatMessage(method)) {
            String msg = (String) args[0];
            return "<h2>" + msg + "</h2>";
        } else {
            throw new IllegalArgumentException("Unable to reimplement method" + method.getName());
        }
    }

    private boolean isFormatMessage(Method method) {
        String methodName = method.getName();
        if (method.getParameterTypes().length != 1) {
            return false;
        }
        if (!"formatMessage".equals(methodName)) {
            return false;
        }
        if (method.getReturnType() != String.class) {
            return false;
        }
        if (method.getParameterTypes()[0] != String.class) {
            return false;
        }
        return true;
    }
}
