package com.example.docusalud.presentation.patients.info

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.docusalud.data.PtesInfo
import com.example.docusalud.databinding.ActivityPtesInfoBinding
import com.example.docusalud.presentation.login.LoginViewModelFactory
import com.example.docusalud.presentation.patients.PatientsViewModel
import com.example.docusalud.presentation.patients.PatientsViewModelFactory
import com.google.android.material.snackbar.Snackbar

class PteInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPtesInfoBinding
    private val viewModel: PatientsViewModel by viewModels(factoryProducer = {
        PatientsViewModelFactory(this)
    })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPtesInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()

    }

    private fun setUpViews() {
        binding.btnSubmitPatient.setOnClickListener {
            val pteInfoMOCK = PtesInfo("Juan", "Perez", "23222111", "12/03/1992",
            "Caja Forense","ac336", 30, "Marcela Gonzalez")


            val pteInfo = PtesInfo(
                name = binding.etName.text.toString(),
                lastName = binding.etLastName.text.toString(),
                dni = binding.etDni.text.toString(),
                fecNac = binding.etFecNac.text.toString(),
                os = binding.etOS.text.toString(),
                osNumber = binding.etOSNumber.text.toString(),
                osSessionsLimit = binding.etOSSessionsLimit.text.toString().toInt(),
                tutorInfo = binding.etTutor.text.toString()
            )
            validateAndSubmitData(pteInfo)

        }
    }

    private fun validateAndSubmitData(pteInfo: PtesInfo) {
        if (pteInfo.name.isNotEmpty() || pteInfo.lastName.isNotEmpty() || pteInfo.dni.isNotEmpty()) {
            viewModel.submitPatient(pteInfo)
        } else {
            Snackbar.make(
                binding.root,
                "Los campos nombre, apellido y dni son obligatorios",
                Snackbar.LENGTH_LONG
            )
                .setAction("Aceptar", null).show()
        }

    }
}