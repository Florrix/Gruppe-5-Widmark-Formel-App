package com.mobileapplications.gruppe_5_widmark_formel_app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ResultDao {
    @Query("SELECT * FROM result_table ORDER BY id ASC")
    fun getAllResultsSortedById(): LiveData<List<Result>>

    @Insert
    suspend fun insert(result: Result)

    @Query("DELETE FROM result_table")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(result: Result)
}