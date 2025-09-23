package com.example.shop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.entity.User;
import com.example.shop.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService srv;

    public UserController(UserService srv) {
        this.srv = srv;
    }

    @GetMapping
    public List<User> all() {
        return srv.getAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return srv.findById(id).orElse(null);
    }

    @PostMapping("/register")
    public User register(@RequestBody User u) {
        return srv.save(u);
    }

    @PatchMapping("/{id}/toggle")
    public User toggle(@PathVariable Long id) {
        User u = srv.findById(id).orElseThrow();
        Boolean current = u.getActive();
        // nếu null thì mặc định là false
        u.setActive(current == null ? true : !current);
        return srv.save(u);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        srv.delete(id);
    }
}
