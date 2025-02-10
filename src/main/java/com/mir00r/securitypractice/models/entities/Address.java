package com.mir00r.securitypractice.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Address {
  private String street;
  private String city;
  private String state;
  private String country;
  @Column(name = "postal_code")
  private String postalCode;
}

