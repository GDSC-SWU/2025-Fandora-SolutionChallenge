package com.example.fandora.data.model.response

data class GoogleLoginResponse(
    val accessToken: String,
    val user: UserInfo
)

data class UserInfo(
    val userId: Int,
    val email: String,
    val name: String
)