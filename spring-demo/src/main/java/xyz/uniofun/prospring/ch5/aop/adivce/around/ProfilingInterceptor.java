package xyz.uniofun.prospring.ch5.aop.adivce.around;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

public class ProfilingInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(methodInvocation.getMethod().getName());
        System.out.println("in ProfilingInterceptor...");
        Object returnValue = methodInvocation.proceed();
        sw.stop();
        dumpInfo(methodInvocation, sw.getTotalTimeMillis());
        return returnValue;
    }

    private void dumpInfo(MethodInvocation invocation, long ms) {
        Method m = invocation.getMethod();
        Object target = invocation.getThis();
        Object[] args = invocation.getArguments();
        System.out.println("Executed method: " + m.getName());
        System.out.println("On object of type: " +
                target.getClass().getName());
        System.out.println("With arguments:");
        for (Object arg : args) {
            System.out.print(" > " + arg);
        }
        System.out.print("\n");
        System.out.println("Took: " + ms + " ms");
    }


}
