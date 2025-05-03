package com.group.Fandora.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String email;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private List<Donation> donations;
}