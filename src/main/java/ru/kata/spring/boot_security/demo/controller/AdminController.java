package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/create_user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @PatchMapping(value = "/edit/{id}")
    public ResponseEntity<User> editUser(@RequestBody User user, @PathVariable("id") Long id) {
        userService.update(id, user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
