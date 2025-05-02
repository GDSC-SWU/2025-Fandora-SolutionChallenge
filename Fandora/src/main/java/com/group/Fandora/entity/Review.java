package com.group.Fandora.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private Long companyId;
    private String reviewImage;
    private String content;
    private LocalDateTime createdAt;
}