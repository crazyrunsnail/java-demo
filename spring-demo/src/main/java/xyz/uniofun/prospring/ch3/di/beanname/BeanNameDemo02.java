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
public class BeanNameDemo02 {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:di/bean-name-02.xml");
        ctx.refresh();
        String s1 = (String) ctx.getBean("john");
        String s2 = (String) ctx.getBean("jon");
        System.out.println(s1 == s2);
        Map<String, String> beansOfType = ctx.getBeansOfType(String.class);
        if (beansOfType.size() == 1) {
            System.out.println("There is only one bean of String");
        }
    }
}
