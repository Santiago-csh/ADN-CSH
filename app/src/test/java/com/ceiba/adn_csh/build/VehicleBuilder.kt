package com.ceiba.adn_csh.build

import com.ceiba.adn_csh.dominio.model.Vehicle

class VehicleBuilder {

    var plate: String? = null
    var cylinderCapacity: Int = 0
    var vehicleType: String

    init {
        this.plate = "LSD528"
        this.cylinderCapacity = 200
        this.vehicleType = ""
    }

    fun createCarPlateGood(): Vehicle{
        return bindVehicleCar("EES999")
    }

    fun createCarPlateBad(): Vehicle{
        return bindVehicleCar("I5S8T")
    }

    fun createCarValidationFirstLetter(): Vehicle{
        return bindVehicleCar("ACJ192")
    }

    fun bindVehicleCar(plate: String): Vehicle{
        this.plate = plate
        return Vehicle(plate, cylinderCapacity, "AUTOMOVIL")
    }

    fun createMotorcyclePlateGood(): Vehicle{
        return bindVehicleMotorcycle("DCF15B", cylinderCapacity)
    }

    fun createMotorcyclePlateBad(): Vehicle{
        return bindVehicleMotorcycle("IYG855", cylinderCapacity)
    }

    fun createMotorcycleCylinderCapacityLessOrEqualsThanFiveHundred(): Vehicle{
        return bindVehicleMotorcycle("IYG85Y", 500)
    }

    fun createMotorcycleCylinderCapacityGreaterThanFiveHundred(): Vehicle{
        return bindVehicleMotorcycle("IYG85G", 501)
    }

    fun bindVehicleMotorcycle(plate: String, cylinderCapacity: Int): Vehicle{
        this.plate = plate
        this.cylinderCapacity = cylinderCapacity
        return Vehicle(plate, cylinderCapacity, "MOTOCICLETA")
    }

}