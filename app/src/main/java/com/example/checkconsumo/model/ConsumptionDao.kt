package com.example.checkconsumo.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.checkconsumo.model.Consumption

@Dao
interface ConsumptionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConsumption(consumption: Consumption)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllConsumption(consumption: List<Consumption>)

    @Update
    suspend fun updateConsumption(consumption: Consumption)

    @Delete
    suspend fun deleteConsumption(consumption: Consumption)

    @Query("DELETE FROM consumption_table")
    suspend fun deleteAllConsumption()

    @Query("SELECT * FROM consumption_table WHERE id = :mId")
    fun getConsumption(mId: Int): LiveData<Consumption>

    @Query("SELECT * FROM consumption_table")
    fun getAllConsumption(): LiveData<List<Consumption>>

}