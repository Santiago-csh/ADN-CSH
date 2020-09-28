package com.ceiba.adn_csh.viewmodel

import androidx.lifecycle.ViewModel
import com.ceiba.domain.model.Rental
import com.ceiba.domain.service.RentalService
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RentalViewModel @Inject constructor(private var rentalService: RentalService): ViewModel() {

    var rentals: Observable<List<Rental>> = rentalService.getActiveRentals()
    lateinit var rental: Rental

    fun createRental(rental: Rental){
        return rentalService.createRental(rental)
    }

    fun updateRentalByPayment(){
        rental = rentalService.updateRentalMakePayment(rental)
    }

}