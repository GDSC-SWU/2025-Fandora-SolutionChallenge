package com.group.Fandora.service;

import com.group.Fandora.dto.DonationRequest;
import com.group.Fandora.entity.Donation;
import com.group.Fandora.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    public Donation createDonation(Long userId, DonationRequest request) {
        Donation donation = new Donation();
        donation.setUserId(userId);
        donation.setCompanyId(request.getCompanyId());
        donation.setArtistName(request.getArtistName());
        donation.setAlbumName(request.getAlbumName());
        donation.setQuantity(request.getQuantity());
        donation.setDonationDate(LocalDate.parse(request.getDonationDate()).atStartOfDay());
        donation.setDonationType(request.getDonationType());
        donation.setStatus("Ongoing");
        donation.setCreatedAt(LocalDateTime.now());

        return donationRepository.save(donation);
    }
}