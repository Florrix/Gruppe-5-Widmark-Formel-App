package com.mobileapplications.gruppe_5_widmark_formel_app.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobileapplications.gruppe_5_widmark_formel_app.database.ResultRepository
import java.lang.IllegalArgumentException

class StartFragmentViewModelFactory (private val resultRepository: ResultRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(StartFragmentViewModel::class.java)){
            return StartFragmentViewModel(resultRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}