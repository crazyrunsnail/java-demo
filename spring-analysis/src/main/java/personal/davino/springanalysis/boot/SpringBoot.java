package personal.davino.springanalysis.boot;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SpringBoot {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext cxt =
                new AnnotationConfigApplicationContext(SpringConfiguration.class);
        Environment environment = cxt.getBean(Environment.class);
        System.out.println(environment.getProperty("spring.boot.version"));
    }


    @Configuration
    public static class SpringConfiguration {

        @Bean
        public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
            PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
            Map<String, String> propertiesMap = new HashMap<>();

            Properties properties = new Properties();
            properties.setProperty("spring.boot.version", "1.0");
            configurer.setProperties(properties);
            return configurer;

        }
    }
}
