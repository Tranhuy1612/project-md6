package com.ra.controller;

import com.ra.model.dto.request.UserLogin;
import com.ra.model.dto.request.UserRegister;
import com.ra.model.dto.response.JwtResponse;
import com.ra.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> handleLogin(@Valid @RequestBody UserLogin userLogin) {
        return new ResponseEntity<>(userService.login(userLogin), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> handleRegister(@Valid @RequestBody UserRegister userRegister) {
        userService.register(userRegister);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> handleLogout(Authentication authentication) {
        return new ResponseEntity<>(userService.handleLogout(authentication), HttpStatus.OK);
    }

}
