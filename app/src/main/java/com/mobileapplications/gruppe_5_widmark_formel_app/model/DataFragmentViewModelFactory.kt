package com.mobileapplications.gruppe_5_widmark_formel_app.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobileapplications.gruppe_5_widmark_formel_app.database.ResultRepository
import java.lang.IllegalArgumentException

class DataFragmentViewModelFactory (private val resultRepository: ResultRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DataFragmentViewModel::class.java)){
            return DataFragmentViewModel(resultRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}