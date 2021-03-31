package com.ceiba.domain.build

import com.ceiba.domain.model.Rental
import com.ceiba.domain.model.Vehicle
import java.util.*
import kotlin.collections.ArrayList

class RentalBuilder {

    var id: Long
    var arrivalDate: Date
    var departureDate: Date
    var price: Double
    var active: Boolean
    lateinit var vehicle: Vehicle
    val calendar: Calendar

    init {
        id = 1
        arrivalDate = Date()
        departureDate = Date()
        price = 0.0
        active = true
        calendar = Calendar.getInstance()
    }

    fun withVehicle(vehicle: Vehicle): RentalBuilder {
        this.vehicle = vehicle
        return this
    }

    fun withDepartureDate(departureDate: Date): RentalBuilder {
        this.departureDate = departureDate
        return this
    }

    fun build(): Rental {
        return Rental(id, vehicle, arrivalDate, departureDate, price, active)
    }

}