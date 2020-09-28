package com.ceiba.adn_csh.domain.repository

import com.ceiba.domain.model.Rental
import io.reactivex.Observable

interface RentalRepository {

    fun createRental(rental: Rental)
    fun rentedVehicle(plate: String): Boolean
    fun getQuantityOfRentedVehiclesByType(vehicleType: String): Int
    fun getActiveRentals(): Observable<List<Rental>>
    fun updateRentalMakePayment(rental: Rental)

}