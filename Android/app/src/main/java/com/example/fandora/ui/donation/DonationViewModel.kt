package com.example.fandora.ui.donation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fandora.data.model.response.CompanyResponse
import com.example.fandora.data.source.DonationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DonationViewModel(private val repository: DonationRepository): ViewModel() {
    private val _companies = MutableStateFlow<List<CompanyResponse>>(emptyList())
    val companies = _companies.asStateFlow()

    fun loadCompanies(accessToken: String) {
        viewModelScope.launch {
            _companies.value = repository.getCompanies(accessToken)
        }
    }
}