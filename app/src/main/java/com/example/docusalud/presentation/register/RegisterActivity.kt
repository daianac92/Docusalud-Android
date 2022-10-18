package com.example.docusalud.presentation.register

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.docusalud.MainActivity
import com.example.docusalud.data.UserLoginAndRegister
import com.example.docusalud.databinding.ActivityRegisterBinding
import com.google.android.material.snackbar.Snackbar

class RegisterActivity: AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels(factoryProducer = {
        RegisterViewModelFactory(this)
    })



    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()
        setUpObservers()
    }

    private fun setUpObservers() {
       viewModel.authState.observe(this@RegisterActivity, Observer {
           when (it){
               AuthState.Success -> {navigateToHome()}
               is AuthState.AuthError -> {showError(it.message)}
               AuthState.Idle -> {}
               AuthState.Loading -> { showProgressBar() }
           }
       })

    }

    private fun showProgressBar() {
        binding.progressBarRegister.isVisible
    }

    private fun showError(message: String?) {
        Snackbar.make(binding.root, message.toString(), Snackbar.LENGTH_LONG)
            .setAction("Aceptar", null)
    }

    private fun navigateToHome() {
        !binding.progressBarRegister.isVisible
        val homeActivity = Intent(this, MainActivity::class.java)
        startActivity(homeActivity)
    }


    private fun setUpViews() {
        val name = binding.etName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val email = binding.etEmailRegister.text.toString()
        val password = binding.etPasswordRegister.text.toString()

        val userInfo = UserLoginAndRegister(name, lastName, email, password)

        binding.btnLogin.setOnClickListener {
            validateDataAndRegister(userInfo)
        }
    }

    private fun validateDataAndRegister(userInfo: UserLoginAndRegister) {
        if  ((userInfo.name).isEmpty()||
            (userInfo.lastName).isEmpty() ||
            (userInfo.email).isEmpty() ||
            (userInfo.password).isEmpty()){
            Snackbar.make(binding.root, "Debe rellenar todos los campos", Snackbar.LENGTH_LONG)
                .setAction("Aceptar", null)
        } else {
            viewModel.handleRegisterUser(userInfo)
        }


    }


}

