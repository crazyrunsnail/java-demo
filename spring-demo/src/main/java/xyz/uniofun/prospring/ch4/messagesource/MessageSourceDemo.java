package xyz.uniofun.prospring.ch4.messagesource;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassRelativeResourceLoader;

import java.util.Locale;

public class MessageSourceDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("di/message-source.xml");
        MessageSource ms = (MessageSource)ctx.getBean("messageSource");
        String nameMsg = ms.getMessage("nameMsg", null, Locale.ENGLISH);
        System.out.println(nameMsg);
        nameMsg = ms.getMessage("nameMsg", null, new Locale("de", "DE"));
        System.out.println(nameMsg);
    }
}
