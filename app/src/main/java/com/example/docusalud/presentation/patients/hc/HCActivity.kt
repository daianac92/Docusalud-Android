package com.example.docusalud.presentation.patients.hc

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.docusalud.databinding.ActivityHcBinding
import com.example.docusalud.mvvm.factory.HCViewModelFactory
import com.example.docusalud.mvvm.viewmodel.HCViewModel

class HCActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHcBinding
    private val viewModel: HCViewModel by viewModels(factoryProducer = {
        HCViewModelFactory(this)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHcBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}