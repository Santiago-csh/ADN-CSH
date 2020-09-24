package com.ceiba.adn_csh.dominio.service.rental.business_responsability_chain.calculate_price_vehicle_chain

import com.ceiba.adn_csh.dominio.model.Rental
import com.ceiba.adn_csh.utilidades.Constantes

class CalculateBasePrice(calculatePricePerCylinder: CalculatePricePerCylinder): CalculatePriceVehicleChain {

    private var calculatePricePerCylinder: CalculatePricePerCylinder
    private var valuePerDayVehicle: Map<String, Double>
    private var valuePerHourVehicle: Map<String, Double>

    init {

        this.calculatePricePerCylinder = calculatePricePerCylinder

        valuePerDayVehicle = mapOf(
            Constantes.CAR to Constantes.VALUE_PER_DAY_CAR,
            Constantes.MOTORCYCLE to Constantes.VALUE_PER_DAY_MOTORCYCLE
        )

        valuePerHourVehicle = mapOf(
            Constantes.CAR to Constantes.VALUE_PER_HOUR_CAR,
            Constantes.MOTORCYCLE to Constantes.VALUE_PER_HOUR_MOTORCYCLE
        )
    }

    override fun addValue(rental: Rental): Double {
        val timeInParking = (rental.departureDate!!.time - rental.arrivalDate!!.time)
        val minutesInParking = timeInParking / 60000
        var hoursInParking = minutesInParking / 60
        var daysInParking = hoursInParking / 24
        if((minutesInParking - (hoursInParking*60)) > 0){
            hoursInParking += 1
        }
        hoursInParking = hoursInParking - (daysInParking*24)
        if(hoursInParking >= 9){
            daysInParking += 1
            hoursInParking = 0
        }
        var price: Double = valuePerDayVehicle[rental.vehicle!!.vehicleType!!.toUpperCase()]!! * daysInParking
        price += valuePerHourVehicle[rental.vehicle!!.vehicleType!!.toUpperCase()]!! * hoursInParking
        return (price + calculatePricePerCylinder.addValue(rental))
    }


}