package com.mir00r.securitypractice.services;

import com.mir00r.securitypractice.models.dtos.JwtResponse;
import com.mir00r.securitypractice.models.dtos.LoginRequest;
import com.mir00r.securitypractice.models.dtos.SignupRequest;
import com.mir00r.securitypractice.models.entities.Role;
import com.mir00r.securitypractice.models.entities.User;
import com.mir00r.securitypractice.repositories.UserRepository;
import com.mir00r.securitypractice.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenUtil jwtTokenUtil;

  public JwtResponse authenticateUser(LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        loginRequest.getUsername(),
        loginRequest.getPassword()
      )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtTokenUtil.generateToken(authentication);
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    return new JwtResponse(
      jwt,
      "Bearer",
      userDetails.getUsername(),
      userDetails.getAuthorities()
    );
  }

  public User registerUser(SignupRequest signupRequest) {
    if (userRepository.existsByUsername(signupRequest.getUsername())) {
      throw new RuntimeException("Username is already taken!");
    }

    Set<Role> roles = signupRequest.getRoles().stream()
      .map(role -> Role.valueOf("ROLE_" + role.toUpperCase()))
      .collect(Collectors.toSet());

    User user = User.builder()
      .username(signupRequest.getUsername())
      .password(passwordEncoder.encode(signupRequest.getPassword()))
      .roles(roles)
      .build();

    return userRepository.save(user);
  }
}
