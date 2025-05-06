package com.example.fandora.data.source.repository

import com.example.fandora.data.model.request.DonationApplyRequest
import com.example.fandora.data.model.response.DonationApplyResponse
import com.example.fandora.data.source.network.RetrofitService

class DonationApplyRepository(private val retrofitService: RetrofitService) {
    suspend fun postDonationApply(accessToken: String, request: DonationApplyRequest): DonationApplyResponse {
        return retrofitService.postDonationApply("Bearer $accessToken", request)
    }
}