package com.yassir.RateMe.Controller;



import com.yassir.RateMe.Model.Entity.User;
import com.yassir.RateMe.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "http://localhost:4200") // Permet la connexion avec Angular

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.verify(user);
    }



}