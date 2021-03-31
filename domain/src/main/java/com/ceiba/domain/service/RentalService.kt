package com.ceiba.domain.service

import com.ceiba.domain.model.Rental

interface RentalService {

    fun createRental(rental: Rental): Long
    fun updateRentalMakePayment(rental: Rental): Rental
    fun getActiveRentals(): List<Rental>
    fun getRental(id: Long): Rental

}