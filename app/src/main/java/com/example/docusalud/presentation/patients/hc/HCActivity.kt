package com.example.docusalud.presentation.patients.hc

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.docusalud.Utils
import com.example.docusalud.data.PtesAnts
import com.example.docusalud.databinding.ActivityHcBinding
import com.example.docusalud.mvvm.factory.HCViewModelFactory
import com.example.docusalud.mvvm.viewmodel.HCViewModel
import com.google.android.material.snackbar.Snackbar

class HCActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHcBinding
    private val viewModel: HCViewModel by viewModels(factoryProducer = {
        HCViewModelFactory(this)
    })
    private lateinit var pteDni: String
    private var isEditable: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHcBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pteDni = intent.extras?.getString(Utils.PATIENT_DNI) ?: ""
        isEditable = intent.extras?.getBoolean(Utils.IS_EDITABLE) ?: true
        setUpViews()
    }

    private fun setUpViews() {
        binding.btnSubmitAnts.setOnClickListener {
            val pteAntsMOCK = PtesAnts(
                "padres y hermanos",
                "Pre nat Lorem impum Lorem impum Lorem impum Lorem impum Lorem impum Lorem impum",
                "Post nat Lorem impum Lorem impum Lorem impum Lorem impum Lorem impum Lorem impum Lorem impum",
                "Health Lorem impum Lorem impum Lorem impum Lorem impum Lorem impum Lorem impum Lorem impum Lorem impum " +
                        "Lorem impum Lorem impum Lorem impum",
                "FamilyRel Lorem impum Lorem impum Lorem impum Lorem impum",
                "School Lorem impum Lorem impum Lorem impum",
                "Obs Lorem impum Lorem impum Lorem impum",
                "10/12/2022"
            )


            val pteAnts = PtesAnts(
                family = binding.etFamily.text.toString(),
                preNat = binding.etPrenatal.text.toString(),
                postNat = binding.etPostNatal.text.toString(),
                health = binding.etHealth.text.toString(),
                familyRel = binding.etFamilyRelationship.text.toString(),
                school = binding.etSchool.text.toString(),
                obs = binding.etObs.text.toString(),
                date = binding.etDate.text.toString()
            )

            validateAndSubmitData(pteAntsMOCK, pteDni)

        }
    }

    private fun validateAndSubmitData(pteAnts: PtesAnts, pteDni: String) {
        if (pteAnts.date.isNotEmpty()) {
            viewModel.submitAnts(pteAnts, pteDni)
        } else {
            Snackbar.make(
                binding.root,
                "Debes indicar una fecha",
                Snackbar.LENGTH_LONG
            )
                .setAction("Aceptar", null).show()
        }

    }
}
