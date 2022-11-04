package com.example.docusalud.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.docusalud.data.UserLogin
import com.example.docusalud.data.Users
import com.example.docusalud.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginViewModel(val repository: LoginRepository): ViewModel() {

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState
    val db = Firebase.firestore

    fun handleLoginUser(userInfo: UserLogin) {
        _authState.value = AuthState.Loading
        FirebaseAuth.getInstance().signInWithEmailAndPassword(userInfo.email, userInfo.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Success
                    db.collection("users").document("36005282").set(Users("Daiana",
                    "Carlantini", "36005282", userInfo.email))
                    Log.d("DATOOOOOO", "signInWithEmail:success")

                } else {
                    _authState.value = AuthState.AuthError("Error al iniciar sesión")
                    Log.d("DATOOOOOO", "signInWithEmail:error")
                }
            }
    }
}