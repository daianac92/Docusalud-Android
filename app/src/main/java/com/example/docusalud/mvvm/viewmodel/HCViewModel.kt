package com.example.docusalud.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.docusalud.data.PtesAnts
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HCViewModel: ViewModel() {

    private val db = Firebase.firestore
    private val patientsHcTable =
        db.collection("users").document("36005282").collection("patientsHcTable")
    private val _ptesAnts = MutableLiveData<PtesAnts>()
    val ptesAnts: LiveData<PtesAnts>
        get() = _ptesAnts

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun submitAnts(pteAnts: PtesAnts, pteDni: String) {
        CoroutineScope(Dispatchers.IO).launch {
            patientsHcTable
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

    fun getAnts(pteDni: String) {
        CoroutineScope(Dispatchers.IO).launch {
            patientsHcTable.document(pteDni).get()
                .addOnSuccessListener { results ->
                    _ptesAnts.postValue(results.toObject<PtesAnts>())
                }
                .addOnFailureListener {
                    _error.postValue(it.message.toString())
                }
        }
    }
}