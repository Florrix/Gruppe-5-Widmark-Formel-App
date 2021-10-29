package com.mobileapplications.gruppe_5_widmark_formel_app.database

import androidx.lifecycle.LiveData

class ResultRepository(private val resultDao: ResultDao) {
    val allResults: LiveData<List<Result>> = resultDao.getAllResultsSortedById()

    suspend fun insert(result: Result){
        resultDao.insert(result)
    }

    suspend fun deleteAll(){
        resultDao.deleteAll()
    }

    suspend fun delete(result: Result){
        resultDao.delete(result)
    }
}