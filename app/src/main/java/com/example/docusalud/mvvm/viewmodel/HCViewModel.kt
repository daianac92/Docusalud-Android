package com.example.docusalud.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.docusalud.data.PtesAnts
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HCViewModel: ViewModel() {

    private val db = Firebase.firestore
    private val patientTable = db.collection("users").document("36005282").collection("patients")


    fun submitAnts(pteAnts: PtesAnts, pteDni: String) {
        CoroutineScope(Dispatchers.IO).launch {
            patientTable
                .document(pteDni)
                .collection("hc")
                .document(pteDni)
                .set(pteAnts)
                .addOnSuccessListener {
                    Log.d("DATOOO", "SUCCESS")
                }
                .addOnFailureListener {
                    Log.d("DATOOO", it.message.toString())
                }

        }
    }
}