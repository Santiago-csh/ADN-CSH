package com.ceiba.infraestructure.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ceiba.domain.model.Rental
import com.ceiba.domain.model.Vehicle
import java.util.*

@Entity(tableName = "rental")
class RentalEntity(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @Embedded(prefix = "vehicle_") var vehicle: Vehicle,
    var arrivalDate: Date,
    var departureDate: Date,
    var price: Double,
    var active: Boolean){

    fun convertRentalEntityToRentalDTO(): Rental {
        return Rental(id, vehicle, arrivalDate, departureDate, price, active)
    }

}