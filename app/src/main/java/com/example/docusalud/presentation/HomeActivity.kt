package com.example.docusalud.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.docusalud.databinding.ActivityHomeBinding
import com.example.docusalud.presentation.patients.info.PteInfoActivity
import com.example.docusalud.presentation.register.RegisterActivity

class HomeActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()

    }

    private fun setUpViews() {
        binding.itemPatient.setOnClickListener {
            val pteInfoActivity = Intent(this, PteInfoActivity::class.java)
            startActivity(pteInfoActivity)
        }
    }

}