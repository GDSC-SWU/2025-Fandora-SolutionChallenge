package com.example.fandora.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fandora.data.model.response.TotalReviewResponse
import com.example.fandora.data.source.TotalReviewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: TotalReviewRepository): ViewModel() {
    private val _totalReviews = MutableStateFlow<List<TotalReviewResponse>>(emptyList())
    val totalReviews= _totalReviews.asStateFlow()

    fun loadTotalReviews(accessToken: String) {
        viewModelScope.launch {
            _totalReviews.value = repository.getTotalReviews(accessToken)
        }
    }
}