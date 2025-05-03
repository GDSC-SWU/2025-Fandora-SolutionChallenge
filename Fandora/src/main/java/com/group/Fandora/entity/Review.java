package com.group.Fandora.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private String reviewImage;
    private String content;
    private LocalDateTime createdAt;
}