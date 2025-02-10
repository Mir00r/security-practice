package com.mir00r.securitypractice.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_sessions")
@Data
public class UserSession extends BaseEntity {
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "session_token")
  private String sessionToken;

  @Column(name = "ip_address")
  private String ipAddress;

  @Column(name = "user_agent")
  private String userAgent;

  @Column(name = "expires_at")
  private LocalDateTime expiresAt;
}
