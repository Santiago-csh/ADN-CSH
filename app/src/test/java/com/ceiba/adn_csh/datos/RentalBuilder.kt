package com.ceiba.adn_csh.datos

import com.ceiba.adn_csh.dominio.model.Rental
import com.ceiba.adn_csh.dominio.model.Vehicle
import java.text.SimpleDateFormat
import java.util.*

class RentalBuilder {

    var id: Long = 0
    var vehicle: Vehicle? = null
    var arrivalDate: Date? = null
    var departureDate: Date? = null

    init {
        val formatoFecha = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")

        id = 1
        arrivalDate = formatoFecha.parse("21-Sep-2020 06:00:00")
        departureDate = formatoFecha.parse("21-Sep-2020 07:00:00")
    }

    fun createRentCarPlateGood(): Rental{
        vehicle = VehicleBuilder().createCarPlateGood()
        return Rental(id, vehicle!!, arrivalDate!!, departureDate!!, 0.0,true)
    }

    fun createRentCarPlateBad(): Rental{
        vehicle = VehicleBuilder().createCarPlateBad()
        return Rental(id, vehicle!!, arrivalDate!!, departureDate!!, 0.0,true)
    }

    fun createRentMotorcyclePlateGood(): Rental{
        vehicle = VehicleBuilder().createMotorcyclePlateGood()
        return Rental(id, vehicle!!, arrivalDate!!, departureDate!!, 0.0,true)
    }

    fun createRentMotorcyclePlateBad(): Rental{
        vehicle = VehicleBuilder().createMotorcyclePlateBad()
        return Rental(id, vehicle!!, arrivalDate!!, departureDate!!, 0.0,true)
    }

    fun createRentCarValidationFirstLetter(): Rental{
        vehicle = VehicleBuilder().createCarValidationFirstLetter()
        return Rental(id, vehicle!!, arrivalDate!!, departureDate!!, 0.0,true)
    }

}