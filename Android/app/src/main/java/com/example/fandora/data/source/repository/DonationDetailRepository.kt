package com.example.fandora.data.source.repository

import com.example.fandora.data.model.response.CompanyDetailResponse
import com.example.fandora.data.model.response.CompanyReviewResponse
import com.example.fandora.data.source.network.RetrofitService

class DonationDetailRepository(private val retrofitService: RetrofitService) {
    suspend fun getCompanyDetail(accessToken: String, companyId: Int): CompanyDetailResponse {
        return retrofitService.getCompanyDetail("Bearer $accessToken", companyId)
    }
    suspend fun getCompanyReviews(accessToken: String, companyId: Int): List<CompanyReviewResponse> {
        return retrofitService.getCompanyReview("Bearer $accessToken", companyId)
    }
}