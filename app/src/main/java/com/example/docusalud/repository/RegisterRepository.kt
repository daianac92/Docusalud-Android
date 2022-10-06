package com.example.docusalud.repository

import android.util.Log
import com.example.docusalud.data.UserRegister
import com.example.docusalud.presentation.register.AuthState
import com.google.firebase.auth.FirebaseAuth

class RegisterRepository {

    fun registerUser(userInfo: UserRegister): AuthState {
       FirebaseAuth.getInstance().createUserWithEmailAndPassword(
           userInfo.email, userInfo.password)
           .addOnCompleteListener { task ->
                if (task.isSuccessful){
                   AuthState.Success
               } else {
                   AuthState.AuthError("Error")
               }
           }

    }
}




