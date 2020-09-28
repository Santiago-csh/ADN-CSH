package com.ceiba.domain.service.businessresponsabilitychain.calculatepricevehicle

import com.ceiba.domain.model.Rental
import java.util.*

class PricePerCylinder: PriceCalculation {

    private var valuePerCylinderCapacityVehicle: Map<String, Double> = mapOf(
        "AUTOMOVIL" to 0.0,
        "MOTOCICLETA" to 2000.0)

    override fun calculatePrice(rental: Rental): Double {
        if(rental.vehicle.cylinderCapacity > 500){
            return valuePerCylinderCapacityVehicle[rental.vehicle.vehicleType.toUpperCase(Locale.ROOT)]!!
        }
        return 0.0
    }

}