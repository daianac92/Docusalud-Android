package com.example.docusalud.mvvm.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.docusalud.mvvm.viewmodel.PatientsViewModel
import com.example.docusalud.presentation.patients.PatientsRepository

class PatientsViewModelFactory constructor(
    private val context: Context
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repository = PatientsRepository()
        return PatientsViewModel(repository) as T
    }
}