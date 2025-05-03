package com.group.Fandora.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private String artistName;
    private String albumName;
    private int quantity;
    private LocalDate donationDate;
    private String donationType;
    private String status;
    private LocalDateTime createdAt;
}