package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserPostCreate {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    private UserPostCreate(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



    @PostConstruct
    private void postConstruct() {
//        userService.save(new User("admin", "admin", 22, "admin", "admin", "123", roles));
    }
}
