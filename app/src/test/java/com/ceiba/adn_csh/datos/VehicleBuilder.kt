package com.ceiba.adn_csh.datos

import com.ceiba.adn_csh.dominio.model.Vehicle

class VehicleBuilder {

    var plate: String? = null
    var cylinderCapacity: Int = 0
    var vehicleType: String? = null

    init {
        this.plate = "LSD528"
        this.cylinderCapacity = 200
        this.vehicleType = ""
    }

    fun createCarPlateGood(): Vehicle{
        this.plate = "EES999"
        this.vehicleType = "AUTOMOVIL"
        return Vehicle(plate!!, cylinderCapacity, vehicleType!!)
    }

    fun createCarPlateBad(): Vehicle{
        this.plate = "I5S8T"
        this.vehicleType = "AUTOMOVIL"
        return Vehicle(plate!!, cylinderCapacity, vehicleType!!)
    }

    fun createCarValidationFirstLetter(): Vehicle{
        this.plate = "ACJ192"
        this.vehicleType = "AUTOMOVIL"
        return Vehicle(plate!!, cylinderCapacity, vehicleType!!)
    }

    fun createMotorcyclePlateGood(): Vehicle{
        this.plate = "DCF15B"
        this.vehicleType = "MOTOCICLETA"
        return Vehicle(plate!!, cylinderCapacity, vehicleType!!)
    }

    fun createMotorcyclePlateBad(): Vehicle{
        this.plate = "IYG855"
        this.vehicleType = "MOTOCICLETA"
        return Vehicle(plate!!, cylinderCapacity, vehicleType!!)
    }

}