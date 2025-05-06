package com.example.fandora.data.source.repository

import com.example.fandora.data.model.response.CompanyResponse
import com.example.fandora.data.source.network.RetrofitService

class DonationRepository(private val retrofitService: RetrofitService) {
    suspend fun getCompanies(accessToken: String): List<CompanyResponse> {
        return retrofitService.getCompany("Bearer $accessToken")
    }
}