package com.ceiba.domain.service.businesscomposite.validationscreaterental

import com.ceiba.domain.exception.RentedVehicleException
import com.ceiba.domain.model.Rental

class RentedVehicleLeaf(private val vehicleExists: Boolean): CreateRentalComponent {

    override fun execute(rental: Rental) {
        if(vehicleExists){
            throw RentedVehicleException()
        }
    }
}