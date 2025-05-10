package com.example.fandora.ui.donationdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fandora.data.model.response.CompanyDetailResponse
import com.example.fandora.data.model.response.CompanyReviewResponse
import com.example.fandora.data.source.repository.DonationDetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DonationDetailViewModel(private val repository: DonationDetailRepository): ViewModel() {
    private val _companyDetail = MutableStateFlow<CompanyDetailResponse?>(null)
    val companyDetail = _companyDetail.asStateFlow()

    private val _companyReview = MutableStateFlow<List<CompanyReviewResponse>>(emptyList())
    val companyReview = _companyReview.asStateFlow()

    fun loadCompanyDetail(accessToken: String, companyId: Int) {
        viewModelScope.launch {
            _companyDetail.value = repository.getCompanyDetail(accessToken, companyId)
        }
    }

    fun loadCompanyReview(accessToken: String, companyId: Int) {
        viewModelScope.launch {
            _companyReview.value = repository.getCompanyReviews(accessToken, companyId)
        }
    }
}