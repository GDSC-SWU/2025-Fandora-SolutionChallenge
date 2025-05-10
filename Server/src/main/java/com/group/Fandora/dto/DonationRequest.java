package com.group.Fandora.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonationRequest {
    private Long companyId;
    private String artistName;
    private String albumName;
    private int quantity;
    private String donationDate;
    private String donationType;
}