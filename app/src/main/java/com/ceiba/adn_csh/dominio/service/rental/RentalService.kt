package com.ceiba.adn_csh.dominio.service.rental

import androidx.lifecycle.LiveData
import com.ceiba.adn_csh.dominio.model.Rental

interface RentalService {

    fun createRental(rental: Rental)
    fun getActiveRentals(): LiveData<List<Rental>>
    fun getActiveRentalById(id: Long): LiveData<Rental>
    fun updateRentalMakePayment(rental: Rental)
    fun calculateVehiclePrice(rental: Rental): Double

}