package com.example.fandora.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fandora.data.source.network.AuthTokenProvider
import com.example.fandora.data.model.response.GoogleLoginResponse
import com.example.fandora.data.source.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val repository = AuthRepository()

    private val _loginResult = MutableStateFlow<GoogleLoginResponse?>(null)
    val loginResult: StateFlow<GoogleLoginResponse?> get() = _loginResult

    fun login(idToken: String) {
        viewModelScope.launch {
            try {
                val response = repository.loginWithGoogle(idToken)
                _loginResult.value = response

                AuthTokenProvider.accessToken = response.accessToken
            } catch (e: Exception) {
                _loginResult.value = null
            }
        }
    }
}