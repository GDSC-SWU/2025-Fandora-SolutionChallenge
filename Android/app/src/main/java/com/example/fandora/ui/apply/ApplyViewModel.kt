package com.example.fandora.ui.apply

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fandora.data.model.request.DonationApplyRequest
import com.example.fandora.data.model.response.DonationApplyResponse
import com.example.fandora.data.source.DonationApplyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ApplyViewModel(private val repository: DonationApplyRepository): ViewModel() {
    private val _applyResponse = MutableStateFlow<DonationApplyResponse?>(null)
    val applyResponse = _applyResponse.asStateFlow()

    fun apply(accessToken: String, request: DonationApplyRequest) {
        viewModelScope.launch {
            _applyResponse.value = repository.postDonationApply(accessToken, request)
        }
    }
}