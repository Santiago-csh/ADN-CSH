package com.ceiba.adn_csh.dominio.service.rental.business_exception_chain

import com.ceiba.adn_csh.dominio.exception.BusinessException
import com.ceiba.adn_csh.dominio.model.Rental

class RentedVehicle(val vehicleExists: Boolean, val parkingSpace: CreateRentalChain): CreateRentalChain {

    override fun validate(rental: Rental): Boolean {
        if(!vehicleExists){
            return parkingSpace.validate(rental)
        }
        throw BusinessException("El vehiculo ya se encuentra alquilado.")
    }
}