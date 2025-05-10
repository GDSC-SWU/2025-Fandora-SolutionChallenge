package com.group.Fandora.dto;

import lombok.Getter;

@Getter
public class DonationHistoryResponse {
    private final Long donationId;
    private final Long companyId;
    private final String companyImage;
    private final String companyName;
    private final String donationDate;

    public DonationHistoryResponse(Long donationId, Long companyId, String companyImage, String companyName, String donationDate) {
        this.donationId = donationId;
        this.companyId = companyId;
        this.companyImage = companyImage;
        this.companyName = companyName;
        this.donationDate = donationDate;
    }
}