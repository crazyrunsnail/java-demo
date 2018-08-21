package xyz.uniofun.prospring.ch3.di.methodinject;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

public class LookupDemo {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(LookupConfig.class);
        ctx.refresh();

        DemoBean abstractBean = ctx.getBean("abstractLookupBean",
                DemoBean.class);
        DemoBean standardBean = ctx.getBean("standardLookupBean",
                DemoBean.class);
        displayInfo("abstractLookupBean", abstractBean);
        displayInfo("standardLookupBean", standardBean);
        ctx.close();
    }

    public static void displayInfo(String beanName, DemoBean bean) {
        Singer singer1 = bean.getSinger();
        Singer singer2 = bean.getSinger();
        System.out.println("" + beanName + ": Singer Instances the Same? "
                + (singer1 == singer2));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("lookupDemo");
        for (int x = 0; x < 100000; x++) {
            Singer singer = bean.getSinger();
            singer.sing();
        }
        stopWatch.stop();
        System.out.println("100000 gets took "
                + stopWatch.getTotalTimeMillis() + " ms");
    }

    @Configuration
    @ComponentScan(basePackages = "xyz.uniofun.prospring.ch3.di")
    public static class LookupConfig {
    }

}
