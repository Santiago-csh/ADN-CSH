package com.ceiba.adn_csh.dominio.service.rental

import androidx.lifecycle.LiveData
import com.ceiba.adn_csh.dominio.model.Rental

interface RentalService {

    fun createRental(rental: Rental)
    fun getActiveRentals(): LiveData<List<Rental>>

}