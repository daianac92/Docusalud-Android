package com.example.docusalud.presentation.patients

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.docusalud.data.PtesInfo
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatientsViewModel(repository: PatientsRepository): ViewModel() {

    private val db = Firebase.firestore
    private val patientTable = db.collection("users").document("36005282").collection("patients")

    fun submitPatient(pteInfo: PtesInfo) {
        CoroutineScope(Dispatchers.IO).launch{
            try {
                patientTable
                    .document(pteInfo.dni)
                    .set(pteInfo)
                Log.d("DATOOO","SUCCESS")

            } catch (e:Exception){
                Log.d("DATOOO", e.toString())
            }

        }

    }

}