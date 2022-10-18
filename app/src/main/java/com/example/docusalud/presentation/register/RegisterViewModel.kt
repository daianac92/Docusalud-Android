package com.example.docusalud.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.docusalud.data.UserLoginAndRegister
import com.example.docusalud.repository.RegisterRepository

class RegisterViewModel(private val repository: RegisterRepository): ViewModel() {

    private val _authState by lazy { MutableLiveData<AuthState>(AuthState.Idle) }
    val authState: LiveData<AuthState> = _authState

    fun handleRegisterUser(userInfo: UserLoginAndRegister){
        _authState.value = AuthState.Loading
        when(repository.registerUser(userInfo)){
            is AuthState.AuthError -> _authState.value = AuthState.AuthError(message = "Error al registrarse. Intente nuevamente")
            AuthState.Idle -> {}
            AuthState.Loading -> {}
            AuthState.Success -> {_authState.value = AuthState.Success}
        }
    }

}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    class AuthError(val message: String? = null) : AuthState()
}