package com.growsync.auth;

import com.growsync.security.JwtService;
import com.growsync.security.SecurityUser;
import com.growsync.user.Role;
import com.growsync.user.User;
import com.growsync.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user = userRepository.findByEmail(request.email())
                .orElseThrow();

        String token = jwtService.generateToken(
                new SecurityUser(user)
        );

        return new AuthResponse(token);
    }
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {

            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .email(request.email())
                .password(
                        passwordEncoder.encode(request.password())
                )
                .role(Role.USER)
                .enabled(true)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(
                new SecurityUser(user)
        );

        return new AuthResponse(token);
    }
}