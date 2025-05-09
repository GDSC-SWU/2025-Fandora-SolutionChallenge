package com.example.fandora.ui.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fandora.data.source.repository.MyPageRepository

class MyPageViewModelFactory(
    private val repository: MyPageRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyPageViewModel::class.java)) {
            return MyPageViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}