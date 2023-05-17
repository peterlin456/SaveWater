package com.savewater.backend.controllers;

import com.savewater.backend.models.Projects;
import com.savewater.backend.repository.ProjectsRepository;
import com.savewater.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class AdminController {

    @Autowired
    private UserService userService;


    @Autowired
    private ProjectsRepository projectsRepository;

    @GetMapping("/projects-list")
    public List<Projects> findProjects() {
        return projectsRepository.findAll();
    }

    //add an event
    @PostMapping("/add-project")
    public Projects createProjects(@Valid @RequestBody Projects project) {
        return projectsRepository.save(project);
    }

    //update an event
    @PutMapping("/projects/{id}")
    public ResponseEntity<Projects> updateProject(@PathVariable(value = "id") Long projectId,
                                                  @Valid @RequestBody Projects projectsDetails) throws ResourceNotFoundException {
        Projects project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + projectId));

        project.setId(projectsDetails.getId());
        project.setName(projectsDetails.getName());
        project.setDate(projectsDetails.getDate());
        project.setAddress(project.getAddress());
        project.setImageUrl(project.getImageUrl());
        project.setDescription(projectsDetails.getDescription());
        final Projects updatedProject = projectsRepository.save(project);
        return ResponseEntity.ok(updatedProject);
    }

    //delete an event
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
