package com.thomas.walmarttest.view.viewholders

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.thomas.walmarttest.databinding.ViewHolderCountryBinding
import com.thomas.walmarttest.model.Country

class CountryViewHolder(val binding: ViewHolderCountryBinding):
ViewHolder(binding.root) {
    fun bind(country: Country) {
        with(binding) {
            tvCountryNameRegion.text = "${country.name}, ${country.region}"
            tvCountryCode.text = country.code
            tvCapital.text = country.capital
        }
    }
}