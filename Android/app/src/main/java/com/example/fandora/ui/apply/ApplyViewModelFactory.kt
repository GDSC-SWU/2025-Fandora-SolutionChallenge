package com.example.fandora.ui.apply

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fandora.data.source.repository.DonationApplyRepository

class ApplyViewModelFactory(
    private val repository: DonationApplyRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApplyViewModel::class.java)) {
            return ApplyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}