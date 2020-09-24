package com.ceiba.adn_csh.infraestructure.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ceiba.adn_csh.dominio.model.Rental
import com.ceiba.adn_csh.dominio.service.rental.RentalService
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RentalViewModel @Inject constructor(rentalService: RentalService): ViewModel() {

    var rentalService: RentalService
    var rentals: LiveData<List<Rental>>
    lateinit var rental: LiveData<Rental>

    init {
        this.rentalService = rentalService
        this.rentals = rentalService.getActiveRentals()
    }

    @Throws(Exception::class)
    fun createRental(rental: Rental){
        return rentalService.createRental(rental)
    }

    fun getActiveRentalById(id: Long){
        rental = rentalService.getActiveRentalById(id)
    }

    @Throws(Exception::class)
    fun updateRentalByPayment(){
        rentalService.updateRentalMakePayment(rental.value!!)
    }

}