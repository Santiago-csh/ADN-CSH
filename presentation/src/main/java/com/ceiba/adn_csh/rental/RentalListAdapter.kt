package com.ceiba.adn_csh.rental

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ceiba.adn_csh.R
import com.ceiba.adn_csh.databinding.RentalListItemBinding
import com.ceiba.adn_csh.common.DataBoundListAdapter
import com.ceiba.domain.model.Rental
import java.util.*

class RentalListAdapter(private var rentalDetailClickCallback: RentalDetailClickCallback): DataBoundListAdapter<Rental, RentalListItemBinding>() {

    override fun createBinding(parent: ViewGroup): RentalListItemBinding {
        val rentalListItemBinding: RentalListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.rental_list_item, parent, false)
        rentalListItemBinding.root.setOnClickListener {
            val rental = rentalListItemBinding.rental
            if(rental != null){
                rentalDetailClickCallback.onClick(rental)
            }
        }
        return rentalListItemBinding
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

    interface RentalDetailClickCallback {
        fun onClick(rental: Rental)
    }

}