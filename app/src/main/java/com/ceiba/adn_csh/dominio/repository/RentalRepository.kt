package com.ceiba.adn_csh.dominio.repository

import androidx.lifecycle.LiveData
import com.ceiba.adn_csh.dominio.model.Rental

interface RentalRepository {

    fun createRental(rental: Rental)
    fun rentedVehicle(plate: String): Boolean
    fun getQuantityOfRentedVehiclesByType(vehicleType: String): Int
    fun getActiveRentals(): LiveData<List<Rental>>

}