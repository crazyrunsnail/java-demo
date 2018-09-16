package xyz.uniofun.prospring.ch6.jpa;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

import javax.sql.DataSource;

public class JpaDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(JpaConfiguration.class);
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        Assert.notNull(dataSource, "DataSource is null");
        TransactionService service = ctx.getBean(TransactionService.class);
        DataSourceTransactionService dataSourceTransactionService = ctx.getBean(DataSourceTransactionService.class);
        service.service();
        dataSourceTransactionService.service();
    }
}