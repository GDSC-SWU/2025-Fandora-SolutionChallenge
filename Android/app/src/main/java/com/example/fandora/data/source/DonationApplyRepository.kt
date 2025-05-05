package com.example.fandora.data.source

import com.example.fandora.data.model.request.DonationApplyRequest
import com.example.fandora.data.model.response.DonationApplyResponse

class DonationApplyRepository(private val retrofitService: RetrofitService) {
    suspend fun postDonationApply(accessToken: String, request: DonationApplyRequest): DonationApplyResponse {
        return retrofitService.postDonationApply("Bearer $accessToken", request)
    }
}