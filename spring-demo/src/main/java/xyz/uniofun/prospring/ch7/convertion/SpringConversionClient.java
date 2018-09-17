package xyz.uniofun.prospring.ch7.convertion;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import xyz.uniofun.prospring.ch7.Singer;

public class SpringConversionClient {

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        Singer singer = ctx.getBean(Singer.class);
        System.out.println(singer);
    }
}
