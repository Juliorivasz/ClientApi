package com.example.clientApi.controller;

import com.example.clientApi.dto.UserDTO;
import com.example.clientApi.dto.UserResponseDTO;
import com.example.clientApi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api/client")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers() {
        List<UserResponseDTO> users = authService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay usuarios registrados.");
        }
        return ResponseEntity.ok(users);
    }



    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        try{
            String register = authService.register(userDTO);
            return ResponseEntity.ok().body(register);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        try {
            String token = authService.login(userDTO);
            return ResponseEntity.ok().body(Collections.singletonMap("token", token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}

