package com.example.fandora.ui.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fandora.data.model.response.DonationResponse
import com.example.fandora.data.model.response.UserNameResponse
import com.example.fandora.data.source.MyPageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyPageViewModel(private val repository: MyPageRepository): ViewModel() {
    private val _userName = MutableStateFlow<UserNameResponse?>(null)
    val userName = _userName.asStateFlow()

    private val _ongoing = MutableStateFlow<List<DonationResponse>>(emptyList())
    val ongoing = _ongoing.asStateFlow()

    private val _donated = MutableStateFlow<List<DonationResponse>>(emptyList())
    val donated = _ongoing.asStateFlow()

    fun loadUserName(accessToken: String) {
        viewModelScope.launch {
            _userName.value = repository.getUserName(accessToken)
        }
    }

    fun loadOngoing(accessToken: String) {
        viewModelScope.launch {
            _ongoing.value = repository.getOngoings(accessToken)
        }
    }

    fun loadDonated(accessToken: String) {
        viewModelScope.launch {
            _donated.value = repository.getDonated(accessToken)
        }
    }
}