package com.mir00r.securitypractice.beans;

import com.mir00r.securitypractice.models.dtos.CustomAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    CustomAuthenticationToken token = (CustomAuthenticationToken) authentication;
    // Custom authentication logic
    return new CustomAuthenticationToken(token.getPrincipal(), token.getCredentials(),
      Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return CustomAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
