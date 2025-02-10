package com.mir00r.securitypractice.models.entities;

import com.mir00r.securitypractice.models.enums.PrivilegeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "privileges")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Privilege extends BaseEntity {
  @Column(unique = true)
  private String name;

  private String description;

  @Column(name = "resource_name")
  private String resourceName;

  @Column(name = "operation_name")
  private String operationName;

  @Enumerated(EnumType.STRING)
  private PrivilegeType type;

  @Column(name = "is_system_privilege")
  private boolean isSystemPrivilege;
}
