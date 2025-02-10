package com.mir00r.securitypractice.models.dtos;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class LdapAuthenticationToken extends AbstractAuthenticationToken {
  private final Object principal;
  private final Object credentials;

  public LdapAuthenticationToken(Object principal, Object credentials) {
    super(null);
    this.principal = principal;
    this.credentials = credentials;
    setAuthenticated(false);
  }

  @Override
  public Object getCredentials() {
    return credentials;
  }

  @Override
  public Object getPrincipal() {
    return principal;
  }
}
