package com.example.docusalud.repository

import com.example.docusalud.data.UserLoginAndRegister
import com.example.docusalud.mvvm.viewmodel.AuthState
import com.google.firebase.auth.FirebaseAuth

class RegisterRepository {

    fun registerUser(userInfo: UserLoginAndRegister): AuthState {
       FirebaseAuth.getInstance().createUserWithEmailAndPassword(
           userInfo.email, userInfo.password)
           .addOnCompleteListener { task ->
                if (task.isSuccessful){
                   AuthState.Success
               } else {
                   AuthState.AuthError("Error")
               }
           }
        return AuthState.AuthError("Error")
    }
}




