package com.ceiba.domain.build

import com.ceiba.domain.model.Vehicle

class VehicleBuilder {

    var plate: String
    var cylinderCapacity: Int = 0
    var vehicleType: String

    init {
        this.plate = "EES999"
        this.cylinderCapacity = 501
        this.vehicleType = "AUTOMOVIL"
    }

    fun withPlate(plate: String): VehicleBuilder {
        this.plate = plate
        return this
    }

    fun withCylinderCapacity(cylinderCapacity: Int): VehicleBuilder {
        this.cylinderCapacity = cylinderCapacity
        return this
    }

    fun withVehicleType(vehicleType: String): VehicleBuilder {
        this.vehicleType = vehicleType
        return this
    }

    fun build(): Vehicle{
        return Vehicle(plate, cylinderCapacity, vehicleType)
    }

}