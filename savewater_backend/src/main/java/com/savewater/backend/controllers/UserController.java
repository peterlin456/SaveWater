package com.savewater.backend.controllers;

import com.savewater.backend.models.Projects;
import com.savewater.backend.repository.ProjectsRepository;
import com.savewater.backend.repository.UserRepository;
import com.savewater.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private  ProjectsRepository projectsRepository;


    //show all events
    @GetMapping("/projects-list")
    public List<Projects> findProjects() {
        return projectsRepository.findAll();
    }


    
    // two users(user and admin)
    // two different dashboards

    //show all events, then the user can register for the event.
    @GetMapping("/{username}/projects-list")
    public List<Projects> listOfProjects() {
        return projectsRepository.findAll();
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

        return new ResponseEntity<>(result, HttpStatus.OK);
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

//    @DeleteMapping("/projects/{id}")
//    public Map<String, Boolean> deleteProject(@PathVariable(value = "id") Long projectId)
//            throws ResourceNotFoundException {
//        Projects project = projectsRepository.findById(projectId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + projectId));
//
//        projectsRepository.delete(project);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }

}
