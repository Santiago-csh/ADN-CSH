package com.ceiba.adn_csh.presentation.rental

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ceiba.adn_csh.R
import com.ceiba.adn_csh.databinding.RentalListItemBinding
import com.ceiba.adn_csh.dominio.model.Rental
import com.ceiba.adn_csh.presentation.common.DataBoundListAdapter
import java.util.*

class RentalListAdapter: DataBoundListAdapter<Rental, RentalListItemBinding>() {

    override fun createBinding(parent: ViewGroup): RentalListItemBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.rental_list_item, parent, false)
    }

    override fun bind(binding: RentalListItemBinding, item: Rental) {
        binding.rental = item
    }

    override fun areItemsTheSame(oldItem: Rental, newItem: Rental): Boolean {
        return Objects.equals(oldItem.id, newItem.id) &&
                Objects.equals(oldItem.vehicle!!.plate!!, newItem.vehicle!!.plate!!)
    }

    override fun areContentsTheSame(oldItem: Rental, newItem: Rental): Boolean {
        return Objects.equals(oldItem.active, newItem.active) &&
                Objects.equals(oldItem.departureDate, newItem.departureDate)
    }

}