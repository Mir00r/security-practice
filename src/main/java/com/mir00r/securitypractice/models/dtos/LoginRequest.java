package com.mir00r.securitypractice.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "Login request payload")
@Data
public class LoginRequest {
  @Schema(description = "User's username", example = "john.doe")
  @NotBlank(message = "Username is required")
  private String username;

  @Schema(description = "User's password", example = "password123")
  @NotBlank(message = "Password is required")
  private String password;
}
