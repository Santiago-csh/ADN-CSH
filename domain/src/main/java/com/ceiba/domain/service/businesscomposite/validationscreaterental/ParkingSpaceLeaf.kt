package com.ceiba.domain.service.businesscomposite.validationscreaterental

import com.ceiba.domain.exception.ParkingSpaceException
import com.ceiba.domain.model.Rental
import java.util.*

private const val CAR = "AUTOMOVIL"
private const val MOTORCYCLE = "MOTOCICLETA"
private const val AMOUNT_OF_CAR_SPACE = 20
private const val AMOUNT_OF_MOTORCYCLE_SPACE = 10
private val AMOUNT_OF_VEHICLE_SPACE = mapOf(CAR to AMOUNT_OF_CAR_SPACE, MOTORCYCLE to AMOUNT_OF_MOTORCYCLE_SPACE)

class ParkingSpace(private val numberOfVehiclesCreated: Int): CreateRentalComponent {

    override fun execute(rental: Rental) {
        for((key, value) in AMOUNT_OF_VEHICLE_SPACE){
            if(key == rental.vehicle.vehicleType.toUpperCase(Locale.ROOT) && numberOfVehiclesCreated >= value){
                throw ParkingSpaceException(rental.vehicle.vehicleType.toLowerCase(Locale.ROOT))
            }
        }
    }
}