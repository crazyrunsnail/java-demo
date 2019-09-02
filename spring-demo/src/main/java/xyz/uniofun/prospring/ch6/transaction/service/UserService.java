package xyz.uniofun.prospring.ch6.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private LoginService loginService;

    @Transactional
    public void addUser() {
        try {
            loginService.addLogin();
        } catch (Exception ignored) {

        }
    }
}
