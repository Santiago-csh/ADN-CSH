package com.ceiba.adn_csh.dominio.service.rental.business_responsability_chain.create_rental_chain

import com.ceiba.adn_csh.dominio.exception.BusinessException
import com.ceiba.adn_csh.dominio.model.Rental
import com.ceiba.adn_csh.utilidades.Constantes
import java.util.regex.Pattern

class PlateFormat(val firstPlateLetter: CreateRentalChain):
    CreateRentalChain {

    private var vehiclePlateFormat: Map<String, String>

    init {
        vehiclePlateFormat = mapOf(
            Constantes.CAR to Constantes.REGULAR_EXPRESSION_CARS,
            Constantes.MOTORCYCLE to Constantes.REGULAR_EXPRESSION_MOTORCYCLES)
    }

    override fun validate(rental: Rental): Boolean {
        for((key, value) in vehiclePlateFormat){
            if(key == rental.vehicle!!.vehicleType!!.toUpperCase()){
                val pattern = Pattern.compile(value)
                if(pattern.matcher(rental.vehicle!!.plate!!).matches()){
                    return firstPlateLetter.validate(rental)
                }
            }
        }
        throw BusinessException("La placa del vehiculo no es valida.")
    }

}