package com.example.clientApi.service;

import com.example.clientApi.config.JwtUtil;
import com.example.clientApi.dto.UserDTO;
import com.example.clientApi.dto.UserResponseDTO;
import com.example.clientApi.entity.User;
import com.example.clientApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getEmail()))
                .collect(Collectors.toList());
    }




    public String register(UserDTO userDTO) {
        Optional<User> existing = userRepository.findByEmail(userDTO.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("El usuario ya existe.");
        }

        User user = User.builder()
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();

        userRepository.save(user);
        return "Usuario registrado con éxito.";
    }

    @Autowired
    private JwtUtil jwtUtil;

    public String login(UserDTO userDTO) {
        Optional<User> user = userRepository.findByEmail(userDTO.getEmail());

        if (user.isPresent()) {
            String storedPassword = user.get().getPassword();
            String enteredPassword = userDTO.getPassword();

            if (passwordEncoder.matches(enteredPassword, storedPassword)) {
                System.out.println(jwtUtil.generateToken(userDTO.getEmail()));
                return jwtUtil.generateToken(userDTO.getEmail());
            }

        }

        throw new RuntimeException("Credenciales inválidasu");
    }


}

