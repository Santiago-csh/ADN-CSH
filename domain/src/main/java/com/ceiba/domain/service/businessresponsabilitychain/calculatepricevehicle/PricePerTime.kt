package com.ceiba.domain.service.businessresponsabilitychain.calculatepricevehicle

import com.ceiba.domain.model.Rental
import java.util.*

class PricePerTime(val next: PriceCalculation): PriceCalculation {

    private var valuePerDayVehicle: Map<String, Double> = mapOf(
        "AUTOMOVIL" to 8000.0,
        "MOTOCICLETA" to 4000.0
    )
    private var valuePerHourVehicle: Map<String, Double> = mapOf(
        "AUTOMOVIL" to 1000.0,
        "MOTOCICLETA" to 500.0
    )

    override fun calculatePrice(rental: Rental): Double {
        val timeInParking = (rental.departureDate.time - rental.arrivalDate.time)
        val minutesInParking = timeInParking / 60000
        var hoursInParking = minutesInParking / 60
        var daysInParking = hoursInParking / 24
        if((minutesInParking - (hoursInParking*60)) > 0){
            hoursInParking += 1
        }
        hoursInParking -= (daysInParking * 24)
        if(hoursInParking >= 9){
            daysInParking += 1
            hoursInParking = 0
        }
        var price: Double = valuePerDayVehicle[rental.vehicle.vehicleType.toUpperCase(Locale.ROOT)]!! * daysInParking
        price += valuePerHourVehicle[rental.vehicle.vehicleType.toUpperCase(Locale.ROOT)]!! * hoursInParking
        price += next.calculatePrice(rental)
        return price
    }
}