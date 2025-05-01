package com.group.Fandora.repository;

import com.group.Fandora.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByUserIdAndStatusOrderByDonationDateDesc(Long userId, String status);
}