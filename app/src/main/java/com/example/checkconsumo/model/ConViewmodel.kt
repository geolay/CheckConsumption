package com.example.checkconsumo.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.checkconsumo.model.ConDataBase
import com.example.checkconsumo.model.ConRepository
import com.example.checkconsumo.model.Consumption
import kotlinx.coroutines.launch

class ConViewmodel(application: Application): AndroidViewModel(application) {

    private val repository: ConRepository
    val allConsumption : LiveData<List<Consumption>>

    init {
       val consumDao = ConDataBase.getDataBase(application).getConDao()
        repository = ConRepository(consumDao)
        allConsumption = repository.listAllConsumption
    }

    fun inserConsum(consumption: Consumption) = viewModelScope.launch {
        repository.insertConsumption(consumption)
    }

    fun deleteAllConsum() = viewModelScope.launch {
        repository.deleteAllConsumption()
    }


    private var selectedConsumption: MutableLiveData<Consumption> = MutableLiveData()

    fun selected(consumption: Consumption?) {
        selectedConsumption.value = consumption

    }

    fun selectedItem(): LiveData<Consumption> = selectedConsumption

    fun updateConsumption(consumption: Consumption) = viewModelScope.launch {
        repository.updateConsumption(consumption)
    }

}