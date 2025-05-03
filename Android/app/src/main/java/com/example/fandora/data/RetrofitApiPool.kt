package com.example.fandora.data

import com.example.fandora.BuildConfig
import com.example.fandora.data.source.RetrofitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApiPool {
    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val token = AuthTokenProvider.accessToken
            val request = chain.request().newBuilder()
                .apply {
                    token?.let {
                        header("Authorization", "Bearer $it")
                    }
                }
                .build()
            chain.proceed(request)
        }
        .addInterceptor(logger)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: RetrofitService = retrofit.create(RetrofitService::class.java)
}