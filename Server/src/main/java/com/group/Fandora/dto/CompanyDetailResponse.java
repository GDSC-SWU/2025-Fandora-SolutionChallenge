package com.group.Fandora.dto;

import lombok.Getter;

@Getter
public class CompanyDetailResponse {
    private final Long companyId;
    private final String companyName;
    private final String address;
    private final String description;
    private final String companyImage;

    public CompanyDetailResponse(Long companyId, String companyName, String address, String description, String companyImage) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.address = address;
        this.description = description;
        this.companyImage = companyImage;
    }
}