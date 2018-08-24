package xyz.uniofun.prospring.ch4.environment;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.refresh();
        ConfigurableEnvironment environment = ctx.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        Map<String, Object> appMap = new HashMap<>();
        appMap.put("user.home", "app_home");

        // addFirst 会覆盖掉原来的配置
        propertySources.addFirst(new MapPropertySource("spring5_MAP", appMap));
        System.out.println("user.home:" + System.getProperty("user.home"));
        System.out.println("JAVA_HOME:" + System.getenv("JAVA_HOME"));

        System.out.println("user.home:" + environment.getProperty("user.home"));
        System.out.println("JAVA_HOME:" + environment.getProperty("JAVA_HOME"));

    }
}
