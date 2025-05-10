package com.group.Fandora.dto;

import lombok.Getter;

@Getter
public class ReviewResponse {
    private final Long reviewId;
    private final String reviewImage;
    private final String content;

    public ReviewResponse(Long reviewId, String reviewImage, String content) {
        this.reviewId = reviewId;
        this.reviewImage = reviewImage;
        this.content = content;
    }
}