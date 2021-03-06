package com.mobileapplications.gruppe_5_widmark_formel_app.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
//Aufbau der Datenbank mit den dazugehörigen Werten
@Entity(tableName = "result_table")
data class Result(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "promille")
    val promille: String,

    @ColumnInfo(name = "weight")
    val weight: String,

    @ColumnInfo(name = "gender")
    val gender: String,

    @ColumnInfo(name = "duration")
    val duration: String,

    @ColumnInfo(name = "quantity")
    val quantity: String
)

