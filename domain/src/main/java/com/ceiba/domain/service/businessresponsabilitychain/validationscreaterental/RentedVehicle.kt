package com.ceiba.domain.service.businessresponsabilitychain.validationscreaterental

import com.ceiba.domain.exception.RentedVehicleException
import com.ceiba.domain.model.Rental

class RentedVehicle(private val vehicleExists: Boolean, private val next: ValidationsCreateRental): ValidationsCreateRental {

    override fun validation(rental: Rental): Boolean {
        if(!vehicleExists){
            return next.validation(rental)
        }
        throw RentedVehicleException("El vehiculo ya se encuentra alquilado.")
    }
}