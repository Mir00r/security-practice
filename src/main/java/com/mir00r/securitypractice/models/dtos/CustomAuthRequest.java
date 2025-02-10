package com.mir00r.securitypractice.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

@Data
@Schema(description = "Custom authentication request")
public class CustomAuthRequest {
  @NotNull
  private String customField;
  private Map<String, String> additionalData;
}
