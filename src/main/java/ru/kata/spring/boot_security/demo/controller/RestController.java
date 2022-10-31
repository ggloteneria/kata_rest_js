package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    private final UserService userService;

    @Autowired
    public RestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id, @ModelAttribute("user") User user) {
        return userService.findById(id);
    }

    @PostMapping("/create_user")
    public User createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return user;
    }

    @PostMapping(value = "/edit/{id}")
    public User editUser(@ModelAttribute("user") User user, @PathVariable("id") Long id, Model model) {
        userService.update(id, user);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findUserByUsername(auth.getName());
        model.addAttribute("currentUser", currentUser);
        return user;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "User successfully deleted";
    }
}
