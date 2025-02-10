package com.mir00r.securitypractice.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Authentication response")
public class AuthResponse {
  private String message;
  private String token;
  private String type = "Bearer";

  public AuthResponse(String message) {
    this.message = message;
  }
}
