package com.example.checkconsumo.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "consumption_table")
data class Consumption (@PrimaryKey(autoGenerate = true)
                       @NonNull
                        var id: Int? = 0,
                        val item: String,
                        val itemPrice: Int?,
                        val quantity: Int?,
                        val total: Int?)

