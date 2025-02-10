package com.mir00r.securitypractice.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Schema(description = "JWT response payload")
@Data
@AllArgsConstructor
public class JwtResponse {
  @Schema(description = "JWT token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
  private String token;

  @Schema(description = "Token type", example = "Bearer")
  private final String type = "Bearer";

  private String username;
  private Collection<? extends GrantedAuthority> roles;
}
