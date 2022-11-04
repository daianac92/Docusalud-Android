package com.example.docusalud.presentation.patients.detail

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.docusalud.data.PtesInfo
import com.example.docusalud.databinding.ActivityPteDetailBinding
import com.example.docusalud.mvvm.viewmodel.PatientsViewModel
import com.example.docusalud.mvvm.factory.PatientsViewModelFactory
import com.example.docusalud.presentation.patients.hc.HCActivity
import com.example.docusalud.presentation.patients.list.PATIENTS_INFO

class PteDetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityPteDetailBinding
    private val viewModel: PatientsViewModel by viewModels(factoryProducer = {
        PatientsViewModelFactory(this)
    })
    private lateinit var pteItem: PtesInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pteItem = intent.extras?.getSerializable(PATIENTS_INFO) as PtesInfo
        setUpViews()

    }

    private fun setUpViews() {
        with(binding){
            tvNamePteDetail.text = "${pteItem.name} ${pteItem.lastName}"
            tvDniPteDetail.text = pteItem.dni
            tvFecNacPteDetail.text = pteItem.fecNac
            tvOSPteDetail.text = pteItem.os
            tvOSNumberPteDetail.text = pteItem.osNumber
            tvOSSessionsLimitNumberPteDetail.text = pteItem.osSessionsLimit.toString()
            icEditHCPteDetail.setOnClickListener { navigateToEditHC() }
            icSeeHCPteDetail.setOnClickListener { navigateToSeeHc() }
            icAddSessionsPteDetail.setOnClickListener { navigateToAddSession() }
            icSeeSessionsPteDetail.setOnClickListener { navigateToSeeSession() }
            icEditSessionsPteDetail.setOnClickListener { navigateToEditSession() }
        }
    }

    private fun navigateToEditSession() {
        TODO("Not yet implemented")
    }

    private fun navigateToSeeSession() {
        TODO("Not yet implemented")
    }

    private fun navigateToAddSession() {
        TODO("Not yet implemented")
    }

    private fun navigateToSeeHc() {
        val hCActivity = Intent(this, HCActivity::class.java).apply {
            putExtra(IS_EDITABLE, false)
        }
        startActivity(hCActivity)
    }

    private fun navigateToEditHC() {
        val hCActivity = Intent(this, HCActivity::class.java).apply {
            putExtra(IS_EDITABLE, true)
        }
        startActivity(hCActivity)
    }


}

const val IS_EDITABLE = "is_editable"