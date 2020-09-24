package com.ceiba.adn_csh.infraestructure.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ceiba.adn_csh.dominio.model.Rental
import com.ceiba.adn_csh.dominio.model.Vehicle
import java.util.*

@Entity(tableName = "rental")
class RentalEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @Embedded(prefix = "vehicle_")
    var vehicle: Vehicle? = null,
    var arrivalDate: Date? = null,
    var departureDate: Date? = null,
    var price: Double = 0.0,
    var active: Boolean = true){

    fun convertRentalEntityToRentalDTO(): Rental{
        return Rental(id, vehicle, arrivalDate, departureDate, price, active)
    }

}