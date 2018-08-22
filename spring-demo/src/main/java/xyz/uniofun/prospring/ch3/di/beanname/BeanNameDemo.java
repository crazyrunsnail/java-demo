package xyz.uniofun.prospring.ch3.di.beanname;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Map;

/**
 * 关于 beanName:
 * <ol>
 *     <li> id</li>
 *     <li> name </li>
 *     <li> classname#0 </li>
 * </ol>
 *
 */
public class BeanNameDemo {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:di/bean-name.xml");
        ctx.refresh();

        Map<String, String> beansOfType = ctx.getBeansOfType(String.class);
        beansOfType.forEach((key, value) ->{
            System.out.println(key);
        });
    }
}
