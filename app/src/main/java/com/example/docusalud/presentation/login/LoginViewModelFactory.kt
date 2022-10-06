package com.example.docusalud.presentation.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.docusalud.repository.LoginRepository

class LoginViewModelFactory constructor(
    private val context: Context
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repository = LoginRepository()
        return LoginViewModel(repository) as T
    }
}