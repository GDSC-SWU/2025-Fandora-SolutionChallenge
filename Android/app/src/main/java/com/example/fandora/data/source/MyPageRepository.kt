package com.example.fandora.data.source

import com.example.fandora.data.model.response.DonationResponse
import com.example.fandora.data.model.response.UserNameResponse

class MyPageRepository(private val retrofitService: RetrofitService) {
    suspend fun getUserName(accessToken: String): UserNameResponse {
        return retrofitService.getUserName("Bearer $accessToken")
    }
    suspend fun getOngoings(accessToken: String): List<DonationResponse> {
        return retrofitService.getOngoing("Bearer $accessToken")
    }
    suspend fun getDonated(accessToken: String): List<DonationResponse> {
        return retrofitService.getDonated("Bearer $accessToken")
    }
}