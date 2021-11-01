package com.mobileapplications.gruppe_5_widmark_formel_app.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobileapplications.gruppe_5_widmark_formel_app.database.Result
import com.mobileapplications.gruppe_5_widmark_formel_app.database.ResultRepository
import kotlinx.coroutines.launch

class DataFragmentViewModel(
    private val repository: ResultRepository
) : ViewModel() {

    var resultPromille = ""
    var resultWeight = ""
    var resultHeight = ""
    var resultGender = ""
    var resultDuration = ""
    var resultQuantity = ""

    fun insert() = viewModelScope.launch {
        repository.insert(
            Result(
                0,
                resultPromille,
                resultWeight,
                resultHeight,
                resultGender,
                resultDuration,
                resultQuantity
            )
        )
    }
}

