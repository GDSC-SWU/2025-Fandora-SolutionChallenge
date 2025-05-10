package com.example.fandora.data.model.response

data class CompanyDetailResponse(
    val companyId: Int,
    val companyName: String,
    val companyImage: String,
    val address: String,
    val description: String
)