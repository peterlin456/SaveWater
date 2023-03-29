package com.savewater.backend.models;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "projects")
public class Projects {
//event title, image, description, time, address

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long Id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;


    @Column(name = "address")
    private String address;

    @Column(name="date")
    private LocalDateTime date;

    @Column(name = "description")
    private String description;

}
