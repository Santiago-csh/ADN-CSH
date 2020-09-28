package com.ceiba.domain.service

import com.ceiba.domain.model.Rental
import io.reactivex.Observable

interface RentalService {

    fun createRental(rental: Rental)
    fun updateRentalMakePayment(rental: Rental): Rental
    fun getActiveRentals(): Observable<List<Rental>>

}