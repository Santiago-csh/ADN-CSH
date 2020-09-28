package com.ceiba.domain.service.businessresponsabilitychain.validationscreaterental

import com.ceiba.domain.exception.ParkingSpaceException
import com.ceiba.domain.model.Rental
import java.lang.StringBuilder
import java.util.*

class ParkingSpace(private val numberOfVehiclesCreated: Int):
    ValidationsCreateRental {

    private var amountOfVehicleSpace: Map<String, Int> = mapOf(
        "AUTOMOVIL" to 20,
        "MOTOCICLETA" to 10)

    override fun validation(rental: Rental): Boolean {
        for((key, value) in amountOfVehicleSpace){
            if(key == rental.vehicle.vehicleType.toUpperCase(Locale.ROOT) && numberOfVehiclesCreated < value){
                return true
            }
        }
        throw ParkingSpaceException(
            StringBuilder().append("Lo sentimos, el parqueadero no tiene espacio para ")
                .append("${rental.vehicle.vehicleType.toLowerCase(Locale.ROOT)}.").toString()
        )
    }

}