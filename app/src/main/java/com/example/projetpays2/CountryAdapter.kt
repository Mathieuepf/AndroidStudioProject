package com.example.projetpays2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view)

class CountryAdapter(private val countries: List<Country>) : RecyclerView.Adapter<CountryViewHolder>() {

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
        val flag = view.findViewById<ImageView>(R.id.imageView_flag_country_view)

        nameCountry.text = country.name
        Glide.with(view.context).load(country.flag).into(flag)

        view.setOnClickListener {
            val context = view.context
            val intent = Intent(context, CountryDetailsActivity::class.java).apply {
                putExtra("countryName", country.name)
                putExtra("countryFlag", country.flag)
            }
            context.startActivity(intent)
        }
    }
}
