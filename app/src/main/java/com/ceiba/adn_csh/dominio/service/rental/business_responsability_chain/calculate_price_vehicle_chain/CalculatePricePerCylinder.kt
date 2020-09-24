package com.ceiba.adn_csh.dominio.service.rental.business_responsability_chain.calculate_price_vehicle_chain

import com.ceiba.adn_csh.dominio.model.Rental
import com.ceiba.adn_csh.utilidades.Constantes

class CalculatePricePerCylinder: CalculatePriceVehicleChain {

    private var valuePerCylinderCapacityVehicle: Map<String, Double>

    init {
        valuePerCylinderCapacityVehicle = mapOf(
            Constantes.CAR to Constantes.VALUE_PER_CYLINDERCAPACITY_CAR,
            Constantes.MOTORCYCLE to Constantes.VALUE_PER_CYLINDERCAPACITY_MOTORCYCLE)
    }

    override fun addValue(rental: Rental): Double {
        if(rental.vehicle!!.cylinderCapacity > 500){
            return valuePerCylinderCapacityVehicle[rental.vehicle!!.vehicleType!!.toUpperCase()]!!
        }
        return 0.0
    }

}