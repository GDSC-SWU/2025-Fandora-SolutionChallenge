package com.group.Fandora.dto;

import lombok.Getter;

@Getter
public class DonationResponse {
    private final Long donationId;
    private final String message;

    public DonationResponse(Long donationId, String message) {
        this.donationId = donationId;
        this.message = message;
    }
}