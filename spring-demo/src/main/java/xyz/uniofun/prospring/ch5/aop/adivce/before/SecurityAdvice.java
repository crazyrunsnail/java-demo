package xyz.uniofun.prospring.ch5.aop.adivce.before;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class SecurityAdvice implements MethodBeforeAdvice {

    public static void main(String[] args) {

        SecurityManager mgr = new SecurityManager();

        SecureBean secureBean = getSecureBean();

        mgr.login("John", "pwd");
        secureBean.writeSecureMessage();;
        mgr.logout();

        try {
            mgr.login("invalid user", "pwd");
            secureBean.writeSecureMessage();
        } catch (SecurityException ex) {
            System.out.println("Exception Caught: " + ex.getMessage());
        } finally {
            mgr.logout();
        }

        try {
            secureBean.writeSecureMessage();
        } catch(SecurityException ex) {
            System.out.println("Exception Caught: " + ex.getMessage());
        }



    }

    private static SecureBean getSecureBean() {
        SecureBean secureBean = new SecureBean();

        ProxyFactory proxyFactory = new ProxyFactory();

        proxyFactory.addAdvice(new SecurityAdvice());
        proxyFactory.setTarget(secureBean);

        return (SecureBean) proxyFactory.getProxy();
    }

    private SecurityManager securityManager;

    public SecurityAdvice() {
        this.securityManager = new SecurityManager();
    }

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        UserInfo userInfo = securityManager.getLoggedOnUser();

        if (userInfo == null) {
            System.out.println("No user authenticated!");
            throw new SecurityException(
                    "You must login before attempting to invoke the method: "
                            + method.getName());
        } else if ("John".equals(userInfo.getUsername())) {
            System.out.println("Logged in user is John - OKAY!");
        } else {
            System.out.println("Logged in user is " + userInfo.getUsername() + " NOT GOOD :(");
            throw  new SecurityException("User " + userInfo.getUsername() + " is not allowed access to " +
                    "method " + method.getName());
        }
    }
}
