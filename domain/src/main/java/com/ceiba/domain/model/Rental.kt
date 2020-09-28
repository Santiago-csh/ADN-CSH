package com.ceiba.domain.model

import java.io.Serializable
import java.util.*

class Rental(
    var id: Long,
    var vehicle: Vehicle,
    var arrivalDate: Date,
    var departureDate: Date,
    var price: Double,
    var active: Boolean): Serializable{
}