package xyz.uniofun.prospring.ch7.format;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import xyz.uniofun.prospring.ch7.Singer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class SpringFormatterClient {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        Singer singer = ctx.getBean(Singer.class);
        System.out.println(singer);
    }

    @Configuration
    @ComponentScan
    public static class Config {
        @Autowired
        ApplicationConversionServiceFactoryBean conversionService;
        @Bean
        public Singer john() throws Exception {
            Singer singer = new Singer();
            singer.setFirstName("John");
            singer.setLastName("Smith");
            singer.setPersonalSite(new URL("http://www.baid.com"));
            singer.setBirthDate(conversionService.getDateTimeFormatter().parse("1977-09-17", Locale.CHINA));
            return singer;
        }
    }
}
