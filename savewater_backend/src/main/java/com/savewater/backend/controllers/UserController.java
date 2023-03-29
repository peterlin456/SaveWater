package com.savewater.backend.controllers;

import com.savewater.backend.models.Projects;
import com.savewater.backend.repository.ProjectsRepository;
import com.savewater.backend.repository.UserRepository;
import com.savewater.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private  ProjectsRepository projectsRepository;

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email) throws MessagingException, UnsupportedEncodingException {

        String response = userService.forgotPassword(email);

        if (!response.startsWith("Invalid")) {
            response = "http://localhost:8080/api/auth/reset-password?token=" + response;
            userService.sendEmail(email,response);
        }

        return response;
    }

    @PutMapping("/reset-password")
    public String resetPassword(@RequestParam String token,
                                @RequestParam String password) {

        return userService.resetPassword(token, password);
    }


    @GetMapping("/projects-list")
    public List<Projects> findProjects() {
        return projectsRepository.findAll();
    }

    @PostMapping("/add-project")
    public Projects createProjects(@Valid @RequestBody Projects project) {
        return projectsRepository.save(project);
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<Projects> updateEmployee(@PathVariable(value = "id") Long projectId,
                                                   @Valid @RequestBody Projects employeeDetails) throws ResourceNotFoundException {
        Projects project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + projectId));

        project.setId(employeeDetails.getId());
        project.setName(employeeDetails.getName());
        project.setDescription(employeeDetails.getDescription());
        final Projects updatedEmployee = projectsRepository.save(project);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/projects/{id}")
    public Map<String, Boolean> deleteProject(@PathVariable(value = "id") Long projectId)
            throws ResourceNotFoundException {
        Projects project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + projectId));

        projectsRepository.delete(project);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }



}
