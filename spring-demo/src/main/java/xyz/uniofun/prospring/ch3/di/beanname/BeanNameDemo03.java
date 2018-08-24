package xyz.uniofun.prospring.ch3.di.beanname;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;
import java.util.Map;

/**
 * id vs name
 *
 * id 会将字符当作是整个id
 * name 将第一个有效的当作是 id, 其余称为 alias
 *
 */
public class BeanNameDemo03 {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:di/bean-name-03.xml");
        ctx.refresh();
        Map<String, String> beansOfType = ctx.getBeansOfType(String.class);

        beansOfType.forEach((key, value) -> {
            System.out.println("key: " + key + "\n alias: " + Arrays.toString(ctx.getAliases(key)));
        });

    }
}
