package com.example.fandora.ui.mypage

import com.example.fandora.data.model.response.DonationResponse

interface DonationClickListener {
    fun onDonationClick(donation: DonationResponse)
}