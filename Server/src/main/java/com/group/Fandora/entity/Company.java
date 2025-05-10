package com.group.Fandora.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    private String companyName;
    private String address;

    @Column(columnDefinition = "TEXT")
    private String description;
    private String companyImage;

    @OneToMany(mappedBy = "company")
    private List<Donation> donations;

    @OneToMany(mappedBy = "company")
    private List<Review> reviews;
}