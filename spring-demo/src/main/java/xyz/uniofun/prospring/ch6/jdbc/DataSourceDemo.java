package xyz.uniofun.prospring.ch6.jdbc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

import javax.sql.DataSource;

public class DataSourceDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(PropertyPlaceConguration.class);
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        Assert.notNull(dataSource, "DataSource is null");

    }
}