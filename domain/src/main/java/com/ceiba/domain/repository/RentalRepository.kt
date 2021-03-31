package com.ceiba.domain.repository

import com.ceiba.domain.model.Rental

interface RentalRepository {

    fun createRental(rental: Rental): Long
    fun rentedVehicle(plate: String): Boolean
    fun getQuantityOfRentedVehiclesByType(vehicleType: String): Int
    fun getActiveRentals(): List<Rental>
    fun getRental(idRental: Long): Rental
    fun updateRentalMakePayment(rental: Rental)

}