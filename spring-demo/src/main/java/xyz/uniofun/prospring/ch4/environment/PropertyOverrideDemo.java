package xyz.uniofun.prospring.ch4.environment;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PropertyOverrideDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("di/properties.xml");
        AppProperty appProperty = ctx.getBean(AppProperty.class);
        System.out.println(appProperty.getApplicationHome());
        System.out.println(appProperty.getUserHome());
        ctx.close();
    }
}
