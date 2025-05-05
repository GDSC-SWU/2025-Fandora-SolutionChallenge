package com.example.fandora.data.model.request

data class DonationApplyRequest(
    val companyId: Int,
    val artistName: String,
    val albumName: String,
    val quantity: Int,
    val donationDate: String,
    val donationType: String
)