package com.mir00r.securitypractice.controllers;

import com.mir00r.securitypractice.models.dtos.AuthResponse;
import com.mir00r.securitypractice.models.dtos.CustomAuthRequest;
import com.mir00r.securitypractice.models.dtos.CustomAuthenticationToken;
import com.mir00r.securitypractice.models.dtos.JwtAuthenticationToken;
import com.mir00r.securitypractice.models.dtos.JwtResponse;
import com.mir00r.securitypractice.models.dtos.LdapAuthenticationToken;
import com.mir00r.securitypractice.models.dtos.LoginRequest;
import com.mir00r.securitypractice.utils.JwtTokenUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenUtil jwtTokenUtil;

  @PostMapping("/database")
  public ResponseEntity<?> authenticateWithDatabase(@Valid @RequestBody LoginRequest request) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtTokenUtil.generateToken(authentication);

    return ResponseEntity.ok(new JwtResponse(jwt));
  }

  @PostMapping("/ldap")
  public ResponseEntity<?> authenticateWithLdap(@Valid @RequestBody LoginRequest request) {
    Authentication authentication = authenticationManager.authenticate(
      new LdapAuthenticationToken(request.getUsername(), request.getPassword())
    );

    return ResponseEntity.ok(new AuthResponse("LDAP authentication successful"));
  }

  @PostMapping("/jwt")
  public ResponseEntity<?> authenticateWithJwt(@RequestHeader("Authorization") String token) {
    Authentication authentication = authenticationManager.authenticate(
      new JwtAuthenticationToken(token.replace("Bearer ", ""))
    );

    return ResponseEntity.ok(new AuthResponse("JWT authentication successful"));
  }

//  @PostMapping("/custom")
//  public ResponseEntity<?> authenticateWithCustomProvider(
//    @Valid @RequestBody CustomAuthRequest request) {
//    Authentication authentication = authenticationManager.authenticate(
//      new CustomAuthenticationToken(request)
//    );
//
//    return ResponseEntity.ok(new AuthResponse("Custom authentication successful"));
//  }
}
