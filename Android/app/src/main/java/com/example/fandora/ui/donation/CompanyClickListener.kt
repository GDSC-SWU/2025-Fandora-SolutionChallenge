package com.example.fandora.ui.donation

import com.example.fandora.data.model.response.CompanyResponse

interface CompanyClickListener {
    fun onCompanyClick(company: CompanyResponse)
}