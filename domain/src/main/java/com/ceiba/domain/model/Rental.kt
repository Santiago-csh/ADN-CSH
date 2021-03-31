package com.ceiba.domain.model

import com.ceiba.domain.exception.InvalidDateException
import com.ceiba.domain.exception.PriceGreaterThanZero
import java.io.Serializable
import java.util.*

class Rental(
    var id: Long,
    var vehicle: Vehicle,
    var arrivalDate: Date = Date(),
    departureDate: Date = Date(),
    price: Double = 0.0,
    var active: Boolean = true) {

    var price: Double = 0.0
        set(newPrice) {
            field = validatePrice(newPrice)
        }

    var departureDate: Date = Date()
        set(newDepartureDate) {
            field = validateDepartureDate(newDepartureDate)
        }


    init {
        this.price = price
        this.departureDate = departureDate
    }

    private fun validatePrice(newPrice: Double): Double {
        validatePriceGreaterThanZero(newPrice)
        return newPrice
    }

    private fun validatePriceGreaterThanZero(newPrice: Double){
        if(newPrice < 0.0){
            throw PriceGreaterThanZero()
        }
    }

    private fun validateDepartureDate(newDepartureDate: Date): Date {
        validateThatTheDepartureDateIsGreaterThanTheArrivalDate(newDepartureDate)
        return newDepartureDate
    }

    private fun validateThatTheDepartureDateIsGreaterThanTheArrivalDate(departureDate: Date){
        if(departureDate.before(arrivalDate)){
            throw InvalidDateException()
        }
    }

}