package com.example.fandora.data.source

import com.example.fandora.data.RetrofitApiPool
import com.example.fandora.data.model.request.GoogleLoginRequest
import com.example.fandora.data.model.response.GoogleLoginResponse

class AuthRepository {
    suspend fun loginWithGoogle(idToken: String): GoogleLoginResponse {
        return RetrofitApiPool.retrofitService.loginWithGoogle(GoogleLoginRequest(idToken))
    }
}