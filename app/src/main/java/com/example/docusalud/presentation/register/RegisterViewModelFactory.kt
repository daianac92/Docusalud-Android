package com.example.docusalud.presentation.register

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.docusalud.presentation.login.LoginViewModel
import com.example.docusalud.repository.RegisterRepository

class RegisterViewModelFactory constructor(
        private val context: Context
    ): ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val repository = RegisterRepository()
            return RegisterViewModel(repository) as T
        }
    }