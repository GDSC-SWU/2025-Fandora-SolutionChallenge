package com.group.Fandora.dto;

import lombok.Getter;

@Getter
public class CompanyListResponse {
    private final Long companyId;
    private final String companyImage;
    private final String companyName;
    private final String description;

    public CompanyListResponse(Long companyId, String companyImage, String companyName, String description) {
        this.companyId = companyId;
        this.companyImage = companyImage;
        this.companyName = companyName;
        this.description = description;
    }
}