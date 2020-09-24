package com.ceiba.adn_csh.dominio.service.rental.business_responsability_chain.create_rental_chain

import com.ceiba.adn_csh.dominio.exception.BusinessException
import com.ceiba.adn_csh.dominio.model.Rental
import com.ceiba.adn_csh.dominio.service.rental.business_responsability_chain.create_rental_chain.CreateRentalChain

class RentedVehicle(val vehicleExists: Boolean, val parkingSpace: CreateRentalChain):
    CreateRentalChain {

    override fun validate(rental: Rental): Boolean {
        if(!vehicleExists){
            return parkingSpace.validate(rental)
        }
        throw BusinessException("El vehiculo ya se encuentra alquilado.")
    }
}