package com.example.checkconsumo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.checkconsumo.databinding.FragmentSecondBinding
import com.example.checkconsumo.model.Consumption
import com.example.checkconsumo.model.ConViewmodel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val viewModel: ConViewmodel by activityViewModels()
    private var idCon: Int = 0
    private var conSelected : Consumption? = null

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedItem().observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.etItemName.setText(it.item)
                binding.etPrecio.setText(it.itemPrice.toString())
                binding.npickerCantidad.value = it.quantity!!
                binding.tvValorTotal.text = it.total.toString()
                Log.d("ID",it.id.toString())
              //  idCon = it.id
                conSelected = it
            }
        })
        binding.btnGuardar.setOnClickListener {
            saveConsumption()
            viewModel.selected(null)
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        //valores del number picker
        binding.npickerCantidad.minValue = 1
        binding.npickerCantidad.maxValue = 30
        binding.npickerCantidad.wrapSelectorWheel = true
    }


    fun saveConsumption() {
        val nombreItem: String = binding.etItemName.text.toString()
        val precioItem: Int = binding.etPrecio.text.toString().toInt()
        binding.npickerCantidad.setOnValueChangedListener { numberPicker, i, i2 ->
            val cantidad = i2
        }
        val total = precioItem * binding.npickerCantidad.value
        if (nombreItem.isEmpty() || precioItem == 0) {
            Toast.makeText(activity, "Debe ingresar todos los datos", Toast.LENGTH_LONG).show()
        } else {
            if (idCon == 0) {
                val newConsumption = Consumption(
                        item = nombreItem,
                        itemPrice = precioItem,
                        quantity = binding.npickerCantidad.value,
                        total = total )
                viewModel.inserConsum(newConsumption)
            } else {
                val newConsumption = Consumption(
                        id = idCon,
                        item = nombreItem,
                        itemPrice = precioItem,
                        quantity = binding.npickerCantidad.value,
                        total = total )
                viewModel.updateConsumption(newConsumption)
            }
        }
    }
}