package com.example.fandora.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Company (
    val id: Int,
    val companyName: String,
    val imageUrl: String,
    val location: String,
    val content: String,
    val date: String
) : Parcelable