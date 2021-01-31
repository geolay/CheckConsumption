package com.example.checkconsumo.model

import androidx.lifecycle.LiveData
import com.example.checkconsumo.model.Consumption
import com.example.checkconsumo.model.ConsumptionDao

class ConRepository(private val consumptionDao: ConsumptionDao) {
    //Contiene todos los datos desde la BBDD
    val listAllConsumption: LiveData<List<Consumption>> = consumptionDao.getAllConsumption()
    //Crea una tarea en la BBDD
    suspend fun insertConsumption(consumption: Consumption){
        consumptionDao.insertConsumption(consumption)
    }
    //Elimina la tarea creada en la BBDD
    suspend fun deleteConsumption(consumption: Consumption){
        consumptionDao.deleteConsumption(consumption)
    }
    //Elimina todos los datos
    suspend fun deleteAllConsumption(){
        consumptionDao.deleteAllConsumption()
    }
    //Actualiza los datos
    suspend fun updateConsumption(consumption: Consumption){
        consumptionDao.updateConsumption(consumption)
    }

}