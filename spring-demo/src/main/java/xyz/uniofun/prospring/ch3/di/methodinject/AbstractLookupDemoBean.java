package xyz.uniofun.prospring.ch3.di.methodinject;


import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component("abstractLookupBean")
public  class AbstractLookupDemoBean implements DemoBean {

    @Override
    @Lookup("singer")
    //
    /**
     * 由CGLib代理加载
     * {@code <bean id="abstractLookupBean"
     *         class="com.apress.prospring5.ch3.AbstractLookupDemoBean">
     *         <lookup-method name="getMySinger" bean="singer"/> </bean>}
     */
    public Singer getSinger(){
        return null;
    }


    @Override
    public void doSomething() {
        getSinger().sing();
    }
}
