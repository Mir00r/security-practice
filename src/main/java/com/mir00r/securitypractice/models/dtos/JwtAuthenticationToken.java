package com.mir00r.securitypractice.models.dtos;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
  private final String token;
  private Object principal;

  public JwtAuthenticationToken(String token) {
    super(null);
    this.token = token;
    setAuthenticated(false);
  }

  @Override
  public Object getCredentials() {
    return token;
  }

  @Override
  public Object getPrincipal() {
    return principal;
  }
}
