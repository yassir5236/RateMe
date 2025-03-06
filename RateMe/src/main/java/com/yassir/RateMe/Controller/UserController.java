package com.yassir.RateMe.Controller;



import com.yassir.RateMe.Dto.User.UserResponseDTO;
import com.yassir.RateMe.Dto.UserRequestDto;
import com.yassir.RateMe.Mapper.IUserMapper;
import com.yassir.RateMe.Model.Entity.User;
import com.yassir.RateMe.Model.Enum.Role;
import com.yassir.RateMe.Security.JwtTokenProvider;
import com.yassir.RateMe.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final IUserMapper userMapper;

    @Autowired
    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider , IUserMapper userMapper) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userMapper = userMapper;
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
    public ResponseEntity<?> login(@RequestBody User user) {
        String token = userService.verify(user);
        if ("Fails".equals(token)) {
            System.out.println("here1");

            return ResponseEntity.status(401).body("Authentication failed");
        }
        System.out.println("here2");

        return ResponseEntity.ok().body(Map.of("token", token));

    }


    @GetMapping("/user/me")
    public UserResponseDTO getCurrentUser(@RequestHeader("Authorization") String token) {
        String username = jwtTokenProvider.extractUserName(token.substring(7)); // Retirer "Bearer "
        Optional<User> user = userService.getUserByUsername(username);

        return userMapper.toResponseDto(user.get());
//        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping(value = "/user/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserResponseDTO updateUser(
            @RequestHeader("Authorization") String token,
            @RequestPart("location") String location,
            @RequestPart("bio") String bio,
            @RequestPart(value = "profilePicture", required = false) MultipartFile profilePicture
    ) {
        String username = jwtTokenProvider.extractUserName(token.substring(7));
        User user = userService.updateUserProfile(username, location, bio, profilePicture);
        return userMapper.toResponseDto(user);

    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            return ResponseEntity.ok(userMapper.toResponseDto(user.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}