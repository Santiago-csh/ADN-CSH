package com.ceiba.adn_csh.dominio.model

import com.ceiba.adn_csh.infraestructure.db.entidades.RentalEntity
import java.util.*

class Rental(
    var id: Long = 0,
    var vehicle: Vehicle? = null,
    var arrivalDate: Date? = null,
    var departureDate: Date? = null,
    var price: Double = 0.0,
    var active: Boolean = true){

    fun convertRentalToRentalEntity(): RentalEntity {
        return RentalEntity(vehicle = vehicle, arrivalDate = arrivalDate)
    }

}