package com.example.checkconsumo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.checkconsumo.databinding.ConsumptionItemBinding
import com.example.checkconsumo.model.Consumption


class ConsumAdapter: RecyclerView.Adapter<ConsumAdapter.ConsumptionVH>() {
    private var mListConsumption = listOf<Consumption>()
    private val selectedConsumption = MutableLiveData<Consumption>()


    fun selectedItem(): LiveData<Consumption> = selectedConsumption

    fun update(listConsumption: List<Consumption>) {
        mListConsumption = listConsumption
        notifyDataSetChanged()
    }

    inner class ConsumptionVH(private val binding: ConsumptionItemBinding):
        RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        fun bind(consumption: Consumption) {
            binding.tvItem.text = consumption.item
            binding.tvItemPrice.text = consumption.itemPrice.toString()
            binding.tvQuantity.text = consumption.quantity.toString()
            binding.tvTotal.text = consumption.total.toString()
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            selectedConsumption.value = mListConsumption[adapterPosition]
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsumptionVH {
        return ConsumptionVH(ConsumptionItemBinding.inflate(LayoutInflater.from(parent.context)))
    }



    override fun onBindViewHolder(holder: ConsumptionVH, position: Int) {
        val consumption = mListConsumption[position]
        holder.bind(consumption)
    }

    override fun getItemCount(): Int = mListConsumption.size

}
