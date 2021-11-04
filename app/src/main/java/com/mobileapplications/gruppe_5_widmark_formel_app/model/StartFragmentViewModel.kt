package com.mobileapplications.gruppe_5_widmark_formel_app.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobileapplications.gruppe_5_widmark_formel_app.database.Result
import com.mobileapplications.gruppe_5_widmark_formel_app.database.ResultRepository
import kotlinx.coroutines.launch

class StartFragmentViewModel(
    private val repository: ResultRepository
) : ViewModel() {
    var resultPromille = ""
    var resultWeight = ""
    var resultGender = ""
    var resultDuration = ""
    var resultQuantity = ""

    fun insert() = viewModelScope.launch {
        repository.insert(
            Result(
                0,
                resultPromille,
                resultWeight,
                resultGender,
                resultDuration,
                resultQuantity
            )
        )
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

}

