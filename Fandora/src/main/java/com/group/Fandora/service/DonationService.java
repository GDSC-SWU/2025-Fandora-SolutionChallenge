package com.group.Fandora.service;

import com.group.Fandora.dto.DonationRequest;
import com.group.Fandora.entity.Company;
import com.group.Fandora.entity.Donation;
import com.group.Fandora.entity.User;
import com.group.Fandora.repository.CompanyRepository;
import com.group.Fandora.repository.DonationRepository;
import com.group.Fandora.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private UserRepository userRepository;  // 🔹 추가

    @Autowired
    private CompanyRepository companyRepository;

    public Donation createDonation(Long userId, DonationRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        Donation donation = new Donation();
        donation.setUser(user);
        donation.setCompany(company);
        donation.setArtistName(request.getArtistName());
        donation.setAlbumName(request.getAlbumName());
        donation.setQuantity(request.getQuantity());
        donation.setDonationDate(LocalDate.parse(request.getDonationDate()));
        donation.setDonationType(request.getDonationType());
        donation.setStatus("Ongoing");
        donation.setCreatedAt(LocalDateTime.now());

        return donationRepository.save(donation);
    }
}