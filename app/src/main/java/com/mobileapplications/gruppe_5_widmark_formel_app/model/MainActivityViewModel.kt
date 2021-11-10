package com.mobileapplications.gruppe_5_widmark_formel_app.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobileapplications.gruppe_5_widmark_formel_app.database.Result
import com.mobileapplications.gruppe_5_widmark_formel_app.database.ResultRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val repository: ResultRepository
) : ViewModel() {

    val results = repository.allResults

    var resultPromille = ""
    var resultWeight = ""
    var resultGender = ""
    var resultDuration = ""
    var resultQuantity = ""
    //Einfüge-Methode der Werte in das Datenbank-Repository
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
    //Methode um alle Ergbenisse zu löschen
    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
    //Methode um ein spezielles Ergbenis zu löschen
    fun deleteResult(result: Result) = viewModelScope.launch {
        repository.delete(result)
    }

}

