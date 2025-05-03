package com.group.Fandora.controller;

import com.group.Fandora.dto.DonationHistoryResponse;
import com.group.Fandora.dto.DonationRequest;
import com.group.Fandora.dto.DonationResponse;
import com.group.Fandora.entity.Company;
import com.group.Fandora.entity.Donation;
import com.group.Fandora.repository.CompanyRepository;
import com.group.Fandora.repository.DonationRepository;
import com.group.Fandora.service.DonationService;
import com.group.Fandora.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/donations")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping
    public ResponseEntity<DonationResponse> submitDonation(@RequestHeader("Authorization") String authorizationHeader,
                                                           @RequestBody DonationRequest request) {
        try {
            String token = authorizationHeader.replace("Bearer ", "");
            Long userId = jwtUtil.validateAndExtractSubject(token);

            Donation donation = donationService.createDonation(userId, request);
            DonationResponse response = new DonationResponse(donation.getDonationId(), "Your donation application has been completed.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/ongoing")
    public ResponseEntity<List<DonationHistoryResponse>> getOngoingDonations(@RequestHeader("Authorization") String authorizationHeader) {
        return getDonationsByStatus(authorizationHeader, "Ongoing");
    }

    @GetMapping("/donated")
    public ResponseEntity<List<DonationHistoryResponse>> getDonatedDonations(@RequestHeader("Authorization") String authorizationHeader) {
        return getDonationsByStatus(authorizationHeader, "Donated");
    }

    private ResponseEntity<List<DonationHistoryResponse>> getDonationsByStatus(String authorizationHeader, String status) {
        try {
            String token = authorizationHeader.replace("Bearer ", "");
            Long userId = jwtUtil.validateAndExtractSubject(token);

            List<Donation> donations = donationRepository.findByUser_UserIdAndStatusOrderByDonationDateDesc(userId, status);
            List<DonationHistoryResponse> response = donations.stream().map(donation -> {
                Company company = donation.getCompany();
                return new DonationHistoryResponse(
                        donation.getDonationId(),
                        company.getCompanyId(),
                        company.getCompanyImage(),
                        company.getCompanyName(),
                        donation.getDonationDate().toString()
                );
            }).collect(Collectors.toList());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }
}