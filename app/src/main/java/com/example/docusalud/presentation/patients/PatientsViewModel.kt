package com.example.docusalud.presentation.patients

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.docusalud.data.PtesInfo
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatientsViewModel(repository: PatientsRepository) : ViewModel() {

    private val db = Firebase.firestore
    private val patientTable = db.collection("users").document("36005282").collection("patients")
    private val _ptesList = MutableLiveData<List<PtesInfo>>()
    val ptesList: LiveData<List<PtesInfo>>
        get() = _ptesList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun submitPatient(pteInfo: PtesInfo) {
        CoroutineScope(Dispatchers.IO).launch {
                patientTable
                    .document(pteInfo.dni)
                    .set(pteInfo)
                    .addOnSuccessListener {
                        Log.d("DATOOO", "SUCCESS")
                    }
                    .addOnFailureListener {
                        Log.d("DATOOO", it.message.toString())
                    }

        }

    }

    fun getPatients() {
        CoroutineScope(Dispatchers.IO).launch {
            patientTable.get()
                .addOnSuccessListener { results ->
                    _ptesList.postValue(results.toObjects(PtesInfo::class.java))
                }
                .addOnFailureListener {
                   _error.postValue(it.message.toString())
                }
        }

    }

}