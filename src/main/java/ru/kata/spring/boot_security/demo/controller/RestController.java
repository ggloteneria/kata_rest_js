package ru.kata.spring.boot_security.demo.controller;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final UserService userService;

    @Autowired
    public RestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> showAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/admin/create_user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @PatchMapping(value = "/admin/edit/{id}")
    public ResponseEntity<User> editUser(@RequestBody User user, @PathVariable("id") Long id) {
        userService.update(id, user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> showInfoAboutUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        return ResponseEntity.ok(user);
    }
}
