package com.ceiba.domain.service.businesscomposite.calculatepricevehicle

import com.ceiba.domain.model.Rental
import java.util.*

private const val CAR = "AUTOMOVIL"
private const val MOTORCYCLE = "MOTOCICLETA"
private const val VALUE_PER_CYLINDER_CAPACITY_CAR = 0.0
private const val VALUE_PER_CYLINDER_CAPACITY_MOTORCYCLE = 2000.0
private val VALUE_PER_CYLINDER_CAPACITY_VEHICLE = mapOf(CAR to VALUE_PER_CYLINDER_CAPACITY_CAR, MOTORCYCLE to VALUE_PER_CYLINDER_CAPACITY_MOTORCYCLE)

class PricePerCylinder: CalculatePriceComponent {

    override fun execute(rental: Rental): Double {
        if(rental.vehicle.cylinderCapacity > 500){
            return VALUE_PER_CYLINDER_CAPACITY_VEHICLE[rental.vehicle.vehicleType.toUpperCase(Locale.ROOT)]!!
        }
        return 0.0
    }

}