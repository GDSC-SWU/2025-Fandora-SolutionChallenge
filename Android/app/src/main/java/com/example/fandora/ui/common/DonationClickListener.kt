package com.example.fandora.ui.common

import com.example.fandora.data.model.response.DonationResponse

interface DonationClickListener {
    fun onDonationClick(donation: DonationResponse)
}