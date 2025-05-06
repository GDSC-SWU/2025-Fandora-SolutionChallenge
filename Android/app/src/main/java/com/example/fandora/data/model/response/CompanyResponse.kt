package com.example.fandora.data.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CompanyResponse(
    val companyId: Int,
    val companyImage: String,
    val companyName: String,
    val description: String
): Parcelable
