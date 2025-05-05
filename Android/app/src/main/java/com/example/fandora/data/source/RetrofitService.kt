package com.example.fandora.data.source

import com.example.fandora.data.model.request.GoogleLoginRequest
import com.example.fandora.data.model.response.GoogleLoginResponse
import com.example.fandora.data.model.response.DonationResponse
import com.example.fandora.data.model.response.TotalReviewResponse
import com.example.fandora.data.model.response.UserNameResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RetrofitService {
    @POST("/auth/google")
    suspend fun loginWithGoogle(
        @Body request: GoogleLoginRequest
    ): GoogleLoginResponse

    @GET("/reviews")
    suspend fun getTotalReview(
        @Header("Authorization") authorization: String
    ): List<TotalReviewResponse>

    @GET("/user/profile")
    suspend fun getUserName(
        @Header("Authorization") authorization: String
    ): UserNameResponse

    @GET("/donations/ongoing")
    suspend fun getOngoing(
        @Header("Authorization") authorization: String
    ): List<DonationResponse>

    @GET("/donations/donated")
    suspend fun getDonated(
        @Header("Authorization") authorization: String
    ): List<DonationResponse>
}