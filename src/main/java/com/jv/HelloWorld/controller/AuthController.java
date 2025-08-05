package com.jv.HelloWorld.controller;

import com.jv.HelloWorld.models.User;
import com.jv.HelloWorld.repository.UserRepository;
import com.jv.HelloWorld.service.UserService;
import com.jv.HelloWorld.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> body){
        String email = body.get("email");
        String password = body.get("password");

        if(userRepository.findByEmail(email).isPresent()){
            return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);  //method 1
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email alreay exists");   //method 2
        }
        userService.createUser(User.builder().email(email).password(password).build());
//        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Registered");
        return new ResponseEntity<>("Successfully Registered", HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> body){
        String email = body.get("email");
        String password = body.get("password");

        var userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            return new ResponseEntity<>("User not Registered", HttpStatus.UNAUTHORIZED);
        }
        User user= userOptional.get();
        if(!passwordEncoder.matches(password, user.getPassword())){
            return new ResponseEntity<>("Invalid User", HttpStatus.UNAUTHORIZED);
        }
        String token = jwtUtil.generateToken(email);
        return ResponseEntity.ok(Map.of("token", token));



    }


}
