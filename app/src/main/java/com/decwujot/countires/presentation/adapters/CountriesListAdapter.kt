package com.decwujot.countires.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.decwujot.countires.R
import com.decwujot.countires.core.data.model.Country
import com.decwujot.countires.databinding.ItemCountryBinding
import com.decwujot.countires.presentation.listeners.CountryListener

class CountriesListAdapter(private val countriesList: ArrayList<Country>) :
    RecyclerView.Adapter<CountriesListAdapter.CountriesViewHolder>(), CountryListener {

    private lateinit var view: CountriesViewHolder
    lateinit var onCountryClickListener: (alphaCode: String) -> Unit

    fun updateCountriesList(newCountriesList: List<Country>) {
        countriesList.clear()
        countriesList.addAll(newCountriesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemCountryBinding>(
            inflater,
            R.layout.item_country,
            parent,
            false
        )
        return CountriesViewHolder(view)
    }

    override fun getItemCount() = countriesList.size

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        view = holder
        holder.view.country = countriesList[position]
        holder.view.listener = this
    }

    override fun onLayoutClicked(v: View) {
        for (country in countriesList) {
            if (country.alphaCode == v.tag) {
                onCountryClickListener.invoke(country.alphaCode)
            }
        }
    }

    class CountriesViewHolder(var view: ItemCountryBinding) : RecyclerView.ViewHolder(view.root)
}