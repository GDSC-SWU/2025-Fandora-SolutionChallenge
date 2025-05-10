package com.example.fandora.data.source.repository

import com.example.fandora.data.model.response.TotalReviewResponse
import com.example.fandora.data.source.network.RetrofitService

class HomeRepository(private val retrofitService: RetrofitService) {
    suspend fun getTotalReviews(accessToken: String): List<TotalReviewResponse> {
        return retrofitService.getTotalReview("Bearer $accessToken")
    }
}