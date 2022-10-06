package com.example.docusalud.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.docusalud.data.UserRegister
import com.example.docusalud.repository.RegisterRepository

class RegisterViewModel(private val repository: RegisterRepository): ViewModel() {
    private val _authState by lazy { MutableLiveData<AuthState>(AuthState.Idle) }
    val authState: LiveData<AuthState> = _authState
    fun handleRegisterUser(userInfo: UserRegister){
        repository.registerUser(userInfo)
    }

}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    class AuthError(val message: String? = null) : AuthState()
}