package com.savewater.backend.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "projects")
public class Projects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long Id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
