package com.savewater.backend.payload.request;

import com.savewater.backend.models.Address;
import lombok.Data;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;

@Data
public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 10)
  private String phone;

  @NotBlank
  private String firstname;

  @NotBlank
  private String lastname;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address homeAddress;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;


}
