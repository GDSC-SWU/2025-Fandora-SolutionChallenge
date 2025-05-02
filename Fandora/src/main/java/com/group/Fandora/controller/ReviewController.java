package com.group.Fandora.controller;

import com.group.Fandora.dto.ReviewResponse;
import com.group.Fandora.entity.Review;
import com.group.Fandora.entity.Company;
import com.group.Fandora.repository.CompanyRepository;
import com.group.Fandora.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/{companyId}/reviews")
    public ResponseEntity<List<ReviewResponse>> getReviewsByCompanyId(@PathVariable Long companyId) {
        companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("Company not found"));

        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        List<ReviewResponse> response = reviews.stream()
                .map(review -> new ReviewResponse(
                        review.getReviewId(),
                        review.getReviewImage(),
                        review.getContent()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}