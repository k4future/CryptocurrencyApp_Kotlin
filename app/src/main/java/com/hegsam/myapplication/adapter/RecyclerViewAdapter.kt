package com.hegsam.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hegsam.myapplication.R
import com.hegsam.myapplication.databinding.RecyclerRowBinding
import com.hegsam.myapplication.model.CryptoModel

class RecyclerViewAdapter (private val cryptoList : ArrayList<CryptoModel>) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {
    inner class RowHolder (val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        return RowHolder(RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.binding.textName.text = cryptoList[position].currency
        holder.binding.textPrice.text = cryptoList[position].price
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context,"Clicked Item : ${cryptoList[position].currency} - ${cryptoList[position].price}",Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }
}