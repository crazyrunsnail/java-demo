package xyz.uniofun.prospring.ch6.transaction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginService {

    /**
     * Progpagation.SUPPORTS 时外层不能 catch 错误:
     * @exception  org.springframework.transaction.UnexpectedRollbackException: Transaction rolled back because it has been marked as rollback-only
     *
     * 使用NESTED
     */
    //@Transactional(propagation = Propagation.SUPPORTS)
    @Transactional(propagation = Propagation.NESTED)
    public void addLogin() {
        throw new RuntimeException();
    }
}
