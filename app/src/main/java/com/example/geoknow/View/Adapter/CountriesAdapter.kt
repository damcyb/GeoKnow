package com.example.geoknow.View.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.geoknow.Model.Data.Country
import com.example.geoknow.R
import com.example.geoknow.Util.getProgressDrawable
import com.example.geoknow.Util.loadImage
import kotlinx.android.synthetic.main.item_country.view.*

class CountriesAdapter(private var countries: ArrayList<Country>): RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    fun updateCountries(newCountries: List<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
    )

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    inner class CountryViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val name = view.countryNameTxt
        private val capital = view.capitalNameTxt
        private val flageImageView = view.flagImageView
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(country: Country) {
            name.text = country.name
            capital.text = country.capital
            flageImageView.loadImage(country.flag, progressDrawable)
        }
    }
}