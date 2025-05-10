package com.group.Fandora.controller;

import com.group.Fandora.dto.CompanyDetailResponse;
import com.group.Fandora.dto.CompanyListResponse;
import com.group.Fandora.entity.Company;
import com.group.Fandora.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyDetailResponse> getCompanyById(@PathVariable Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("Company not found"));

        CompanyDetailResponse response = new CompanyDetailResponse(
                company.getCompanyId(),
                company.getCompanyName(),
                company.getAddress(),
                company.getDescription(),
                company.getCompanyImage()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CompanyListResponse>> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyListResponse> response = companies.stream()
                .map(company -> new CompanyListResponse(
                        company.getCompanyId(),
                        company.getCompanyImage(),
                        company.getCompanyName(),
                        company.getDescription()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}