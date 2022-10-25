package com.example.docusalud.presentation.patients

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PatientsViewModelFactory constructor(
    private val context: Context
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repository = PatientsRepository()
        return PatientsViewModel(repository) as T
    }
}