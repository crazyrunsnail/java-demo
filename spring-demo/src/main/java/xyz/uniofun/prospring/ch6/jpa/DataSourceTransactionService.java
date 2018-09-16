package xyz.uniofun.prospring.ch6.jpa;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DataSourceTransactionService {
    @Transactional
    public void service() {
        System.out.println("DataSourceTransactionService");
    }
}
