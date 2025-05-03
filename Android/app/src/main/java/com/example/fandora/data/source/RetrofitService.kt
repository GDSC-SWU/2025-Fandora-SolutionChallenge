package com.example.fandora.data.source

import com.example.fandora.data.model.request.GoogleLoginRequest
import com.example.fandora.data.model.response.GoogleLoginResponse
import com.example.fandora.data.model.response.TotalReviewResponse
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
}