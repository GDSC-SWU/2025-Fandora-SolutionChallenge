package com.group.Fandora.controller;

import com.group.Fandora.dto.DonationRequest;
import com.group.Fandora.dto.DonationResponse;
import com.group.Fandora.entity.Donation;
import com.group.Fandora.service.DonationService;
import com.group.Fandora.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/donations")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @Autowired
    private JwtUtil jwtUtil;

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
}