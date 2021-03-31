package com.ceiba.domain.service.businesscomposite.calculatepricevehicle

import com.ceiba.domain.model.Rental
import java.util.*

private const val CAR = "AUTOMOVIL"
private const val MOTORCYCLE = "MOTOCICLETA"
private const val VALUE_PER_DAY_CAR = 8000.0
private const val VALUE_PER_DAY_MOTORCYCLE = 4000.0
private const val VALUE_PER_HOUR_CAR = 1000.0
private const val VALUE_PER_HOUR_MOTORCYCLE = 500.0
private val VALUE_PER_DAY_VEHICLE = mapOf(CAR to VALUE_PER_DAY_CAR, MOTORCYCLE to VALUE_PER_DAY_MOTORCYCLE)
private val VALUE_PER_HOUR_VEHICLE = mapOf(CAR to VALUE_PER_HOUR_CAR, MOTORCYCLE to VALUE_PER_HOUR_MOTORCYCLE)

class PricePerTime: CalculatePriceComponent {

    override fun execute(rental: Rental): Double {
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
        var price: Double = VALUE_PER_DAY_VEHICLE[rental.vehicle.vehicleType.toUpperCase(Locale.ROOT)]!! * daysInParking
        price += VALUE_PER_HOUR_VEHICLE[rental.vehicle.vehicleType.toUpperCase(Locale.ROOT)]!! * hoursInParking
        return price
    }
}