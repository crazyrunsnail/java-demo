package xyz.uniofun.prospring.ch7.propertyeditor;

import org.springframework.context.support.GenericXmlApplicationContext;
import xyz.uniofun.prospring.ch7.Singer;

public class PropertyEditorClient {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:convertion/property-editor.xml");
        Singer eric = (Singer)ctx.getBean("eric");
        System.out.println(eric);
        Singer countrySinger = (Singer) ctx.getBean("countrySinger");
        System.out.println(countrySinger);
    }
}
