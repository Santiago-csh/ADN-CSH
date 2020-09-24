package com.ceiba.adn_csh.dominio.service.rental.business_responsability_chain.create_rental_chain

import com.ceiba.adn_csh.dominio.exception.BusinessException
import com.ceiba.adn_csh.dominio.model.Rental
import com.ceiba.adn_csh.utilidades.Constantes

class ParkingSpace(val numberOfVehiclesCreated: Int, val plateFormat: CreateRentalChain):
    CreateRentalChain {

    private var amountOfVehicleSpace: Map<String, Int>

    init {
        amountOfVehicleSpace = mapOf(
            Constantes.CAR to Constantes.MAXIMUM_SPACE_FOR_CARS,
            Constantes.MOTORCYCLE to Constantes.MAXIMUM_SPACE_FOR_MOTORCYCLES)
    }

    override fun validate(rental: Rental): Boolean {
        for((key, value) in amountOfVehicleSpace){
            if(key == rental.vehicle!!.vehicleType!!.toUpperCase() && numberOfVehiclesCreated < value){
                return plateFormat.validate(rental)
            }
        }
        throw BusinessException("Lo sentimos, el parqueadero no tiene espacio para ${rental.vehicle!!.vehicleType!!.toLowerCase()}.")
    }

}