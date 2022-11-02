package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping
//    public ResponseEntity<User> registration(@RequestBody User user) {
//        userService
//    }

    @GetMapping("/user")
    public ResponseEntity<User> showInfoAboutUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        return ResponseEntity.ok(user);
    }

}
