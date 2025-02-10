package com.mir00r.securitypractice.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Schema(description = "Signup request payload")
public class SignupRequest {

  @NotBlank(message = "Username is required")
  @Size(min = 3, max = 50)
  private String username;

  @NotBlank(message = "Password is required")
  @Size(min = 6, max = 100)
  private String password;

  private Set<String> roles;
}
