package com.abdulmoqueet.livecurrencyconverter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.single_list_layout.view.*

class CustomAdapter(val context: Context, val list: List<CurrencyData>) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.single_list_layout, parent, false)
        return MyViewHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currencyName:String = list[position].currency
        val value:Double = String.format("%.2f", list[position].value).toDouble()
        val flag:Int = list[position].flag

        holder.setData(currencyName, value, flag)

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun setData(currencyName: String, value:Double, flag:Int){
            itemView.textview_currency.text = currencyName
            itemView.textview_value.text = value.toString()
            itemView.imagview_flag.setImageResource(flag)
        }

    }

}