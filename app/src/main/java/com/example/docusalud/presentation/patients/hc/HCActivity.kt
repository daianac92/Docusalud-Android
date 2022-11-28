package com.example.docusalud.presentation.patients.hc

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.docusalud.Utils
import com.example.docusalud.data.PtesAnts
import com.example.docusalud.databinding.ActivityHcBinding
import com.example.docusalud.mvvm.factory.HCViewModelFactory
import com.example.docusalud.mvvm.viewmodel.HCViewModel
import com.google.android.material.snackbar.Snackbar

class HCActivity : AppCompatActivity() {
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
        viewModel.getAnts(pteDni)
        setObservers()
    }

    private fun setObservers() {
        viewModel.ptesAnts.observe(this) {
            setUpViews(it)
        }
    }


    private fun setUpViews(info: PtesAnts?) {

        Log.d(" DATOOOO", isEditable.toString())

        with(binding) {
            if (!isEditable) {
                etFamily.isEnabled = false
                etPrenatal.isEnabled = false
                etPostNatal.isEnabled = false
                etSchool.isEnabled = false
                etFamilyRelationship.isEnabled = false
                etObs.isEnabled = false
                etHealth.isEnabled = false
                etDate.isEnabled = false
                btnSubmitAnts.isEnabled = false
            }

            etFamily.setText(info?.family)
            etPrenatal.setText(info?.preNat)
            etPostNatal.setText(info?.postNat)
            etSchool.setText(info?.school)
            etFamilyRelationship.setText(info?.familyRel)
            etObs.setText(info?.obs)
            etDate.setText(info?.date)
            etHealth.setText(info?.health)

            btnSubmitAnts.setOnClickListener {
                val pteAntsMOCK = PtesAnts(
                    "36005282",
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
                    family = etFamily.text.toString(),
                    preNat = etPrenatal.text.toString(),
                    postNat = etPostNatal.text.toString(),
                    health = etHealth.text.toString(),
                    familyRel = etFamilyRelationship.text.toString(),
                    school = etSchool.text.toString(),
                    obs = etObs.text.toString(),
                    date = etDate.text.toString()
                )

                validateAndSubmitData(pteAnts, pteDni)

            }

        }

    }

    private fun validateAndSubmitData(pteAnts: PtesAnts, pteDni: String) {
        if (pteAnts.date.isNotEmpty() == true) {
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
