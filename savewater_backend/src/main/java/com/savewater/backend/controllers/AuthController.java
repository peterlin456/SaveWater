package com.savewater.backend.controllers;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.savewater.backend.models.ERole;
import com.savewater.backend.models.Role;
import com.savewater.backend.models.User;
import com.savewater.backend.payload.request.LoginRequest;
import com.savewater.backend.payload.request.SignupRequest;
import com.savewater.backend.payload.response.JwtResponse;
import com.savewater.backend.payload.response.MessageResponse;
import com.savewater.backend.repository.RoleRepository;
import com.savewater.backend.repository.UserRepository;
import com.savewater.backend.security.jwt.JwtUtils;
import com.savewater.backend.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
               signUpRequest.getEmail(),
               signUpRequest.getPhone(),
               signUpRequest.getFirstname(),
               signUpRequest.getLastname(),
               signUpRequest.getHomeAddress(),
               encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

      roles.add(userRole);
//    } else {
//      strRoles.forEach(role -> {
//        switch (role) {
//        case "admin":
//          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(adminRole);
//
//          break;
//        case "mod":
//          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(modRole);
//
//          break;
//        default:
//          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(userRole);
//        }
//      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }


  // dashboard for phone, address, firstname, lastname, email
  @GetMapping("/{username}/dashboard") //failed
  public ResponseEntity<?> userInfo(@PathVariable String username){
    //method 1 ClassCastException
//    AbstractAuthenticationToken auth = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//    UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

    //method 2  received "First Name": "anonymousUser"
//    Authentication auth = SecurityContextHolder.getContext().getAuthentication();


    Map<String, String> result = new HashMap<>();
//    result.put("First Name",userDetails.getFullname());
//    result.put("Last Name", auth.getLastname());
//    result.put("Phone Number", userDetails.getPhone());
//    result.put("Email", userDetails.getEmail());
//    result.put("Address",userDetails.getHomeAddress().toString());

    // method 4:"anonymousUser"
//    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    String name = "";
//    if (principal instanceof UserDetails) {
//       name = ((UserDetails)principal).getUsername();
//    } else {
//       name = principal.toString();
//    }
//    result.put("Name: ", name);

    return new ResponseEntity<>(result,HttpStatus.OK);
  }

  //method 3: java.security.Principal.getName()" because "principal" is null
//  @RequestMapping(value = "/myusername", method = RequestMethod.GET)
//  @ResponseBody
//  public String currentUserName(Principal principal) {
//    return principal.getName();
//  }

  //method 5 :
//  @RequestMapping(value = "/username", method = RequestMethod.GET)
//  @ResponseBody
//  public String currentUserName(Authentication authentication) {
//    return authentication.getName();
//  }


//  @GetMapping("/username") //failed return empty but 200.ok
//  @ResponseBody
//  public String currentUserName(Authentication authentication) {
//
//    if (authentication != null)
//      return authentication.getName();
//    else
//      return "";
//  }
//  @GetMapping("/authentication") //sesseion and remote address ???
//  public Object authentication(@CurrentSecurityContext(expression="authentication")
//                               Authentication authentication) {
//    return authentication.getDetails();
//  }
//  @GetMapping("/hello") //Hello, anonymousUser!
//  public String hello(@CurrentSecurityContext(expression="authentication.name")
//                      String username) {
//    return "Hello, " + username + "!";
//  }
}
