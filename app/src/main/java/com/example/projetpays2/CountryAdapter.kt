package com.example.projetpays2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view)


class CountryAdapter (val countries: List<Country>) : RecyclerView.Adapter<CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.country_view, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        val view = holder.itemView
        val nameCountry = view.findViewById<TextView>(R.id.name_country_textView)

        nameCountry.text = country.name

        val flag = view.findViewById<ImageView>(R.id.imageView_flag_country_view)
        flag.setImageResource(country.getImage())
    }

}