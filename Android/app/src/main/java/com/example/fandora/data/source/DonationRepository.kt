package com.example.fandora.data.source

import com.example.fandora.data.model.response.CompanyResponse

class DonationRepository(private val retrofitService: RetrofitService) {
    suspend fun getCompanies(accessToken: String): List<CompanyResponse> {
        return retrofitService.getCompany("Bearer $accessToken")
    }
}