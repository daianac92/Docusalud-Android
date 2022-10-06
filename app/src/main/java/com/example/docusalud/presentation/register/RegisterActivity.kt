package com.example.docusalud.presentation.register

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.docusalud.data.UserRegister
import com.example.docusalud.databinding.ActivityRegisterBinding

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
    }

    private fun setUpViews() {
        val name = binding.etName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val email = binding.etEmailRegister.text.toString()
        val password = binding.etPasswordRegister.text.toString()

        val userInfo = UserRegister(name, lastName, email, password)

        binding.btnLogin.setOnClickListener {
            validateDataAndRegister()
        }
    }

    private fun validateDataAndRegister(userInfo: UserRegister) {
        TODO("Validate Data")
        viewModel.registerUser(userInfo)
    }


}