package com.ceiba.adn_csh.dominio.model

open class Vehicle(plate: String, cylinderCapacity: Int, vehicleType: String) {

    var plate: String? = null
    var cylinderCapacity: Int = 0
    var vehicleType: String? = null

    init {
        this.plate = plate
        this.cylinderCapacity = cylinderCapacity
        this.vehicleType = vehicleType
    }

}