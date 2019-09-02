package xyz.uniofun.prospring.ch6.transaction;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import xyz.uniofun.prospring.ch6.transaction.service.UserService;

public class UnexpectedRollbackExceptionTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TransactionConfiguration.class);

        UserService userService = context.getBean(UserService.class);
        userService.addUser();

    }
}
