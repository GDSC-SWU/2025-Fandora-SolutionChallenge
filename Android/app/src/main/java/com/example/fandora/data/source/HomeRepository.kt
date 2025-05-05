package com.example.fandora.data.source

import com.example.fandora.data.model.response.TotalReviewResponse

class HomeRepository(private val retrofitService: RetrofitService) {
    suspend fun getTotalReviews(accessToken: String): List<TotalReviewResponse> {
        return retrofitService.getTotalReview("Bearer $accessToken")
    }
}