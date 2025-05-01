package com.group.Fandora.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donationId;

    private Long userId;
    private Long companyId;
    private String artistName;
    private String albumName;
    private int quantity;
    private LocalDateTime donationDate;
    private String donationType;
    private String status;
    private LocalDateTime createdAt;
}