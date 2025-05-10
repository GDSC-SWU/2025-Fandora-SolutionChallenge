package com.example.fandora.data.model.response

data class TotalReviewResponse(
    val reviewId: Int,
    val companyId: Int,
    val reviewImage: String,
    val content: String
)