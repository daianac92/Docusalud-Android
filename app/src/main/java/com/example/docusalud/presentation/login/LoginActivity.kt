package com.example.docusalud.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.docusalud.data.UserLogin
import com.example.docusalud.databinding.ActivityLoginBinding
import com.example.docusalud.mvvm.factory.LoginViewModelFactory
import com.example.docusalud.mvvm.viewmodel.AuthState
import com.example.docusalud.mvvm.viewmodel.LoginViewModel
import com.example.docusalud.presentation.HomeActivity
import com.example.docusalud.presentation.register.RegisterActivity
import com.google.android.material.snackbar.Snackbar

class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels(factoryProducer = {
        LoginViewModelFactory(this)
    })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpObservers()
        setUpViews()
    }

    private fun setUpViews() {
        binding.tvRegister.setOnClickListener {
            val registerActivity = Intent(this, RegisterActivity::class.java)
            startActivity(registerActivity)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmailLogin.text.toString().trim()
            val password = binding.etPasswordLogin.text.toString().trim()

            val userInfo = UserLogin("dai.carlantini@gmail.com", "123456")

            validateDataAndLogin(userInfo)

        }
    }

    /**This function validates the user input and call login service in viewmodel**/
    private fun validateDataAndLogin(userInfo: UserLogin) {

        if ((userInfo.email).isEmpty() ||
            (userInfo.password).isEmpty()
        ) {
            Snackbar.make(binding.root, "Debe rellenar todos los campos", Snackbar.LENGTH_LONG)
                .setAction("Aceptar", null).show()
        } else {
            viewModel.handleLoginUser(userInfo)
        }
    }

    private fun navigateToHome() {
        val homeActivity = Intent(this, HomeActivity::class.java)
        startActivity(homeActivity)
    }


    private fun showError(message: String?) {
        Snackbar.make(binding.root, message.toString(), Snackbar.LENGTH_LONG)
            .setAction("Aceptar", null).show()
    }

    private fun setUpObservers() {
        viewModel.authState.observe(this@LoginActivity, Observer {
            when (it) {
                AuthState.Success -> {
                    navigateToHome()
                }
                is AuthState.AuthError -> {
                    showError(it.message)
                }
                AuthState.Idle -> {}
                AuthState.Loading -> {
                    showProgressBar()
                }
            }
            Log.d("DATOAUTH", it.toString())
        })

    }

    private fun showProgressBar() {
        binding.progressBarLogin.isVisible
    }


}