package com.savewater.backend.models;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email") 
    })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
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

  @NotBlank
  @Size(max = 120)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @Column(name = "reset_password_token")
  private String resetPasswordToken;

  @Column(columnDefinition = "TIMESTAMP")
  private LocalDateTime tokenCreationDate;
  public User() {
  }
// phone, address, firstname, lastname
// dashboard for phone, address, firstname, lastname, email
// forget password? (post and email)


  public User(String email, String phone, String firstname, String lastname, Address homeAddress) {
    this.email = email;
    this.phone = phone;
    this.firstname = firstname;
    this.lastname = lastname;
    this.homeAddress = homeAddress;
  }

  public User(String username, String email, String phone, String firstname, String lastname, Address homeAddress, String password) {
    this.username = username;
    this.email = email;
    this.phone = phone;
    this.firstname = firstname;
    this.lastname = lastname;
    this.homeAddress = homeAddress;
    this.password = password;
  }
}
