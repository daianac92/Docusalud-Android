package com.example.docusalud.presentation.login

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.docusalud.data.UserLoginAndRegister
import com.example.docusalud.databinding.ActivityLoginBinding
import com.example.docusalud.presentation.register.RegisterActivity
import com.google.android.material.snackbar.Snackbar

class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels(factoryProducer = {
        LoginViewModelFactory(this)
    })


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()
    }

    private fun setUpViews() {
        binding.tvRegister.setOnClickListener{
            val registerActivity = Intent(this, RegisterActivity::class.java)
            startActivity(registerActivity)
        }

        val email = binding.etEmailRegister.text.toString()
        val password = binding.etPasswordRegister.text.toString()

        val userInfo = UserLoginAndRegister("", "", email, password)

        binding.btnLogin.setOnClickListener {
            validateDataAndLogin(userInfo)

        }
    }

    private fun validateDataAndLogin(userInfo: UserLoginAndRegister){
        if  ((userInfo.email).isEmpty() ||
            (userInfo.password).isEmpty()){
            Snackbar.make(binding.root, "Debe rellenar todos los campos", Snackbar.LENGTH_LONG)
                .setAction("Aceptar", null)
        } else {
            viewModel.handleLoginUser(userInfo)
        }
    }


}