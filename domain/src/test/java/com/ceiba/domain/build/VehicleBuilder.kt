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

    fun createCarPlateGood(): Vehicle{
        return bindVehicleCar(plate)
    }

    fun createCarPlateBad(): Vehicle{
        return bindVehicleCar("I5S8T")
    }

    fun createCarValidationFirstLetter(): Vehicle{
        return bindVehicleCar("ACJ192")
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
        return bindVehicleMotorcycle("IYG85G", cylinderCapacity)
    }

    fun bindVehicleCar(plate: String): Vehicle{
        this.plate = plate
        this.vehicleType = "AUTOMOVIL"
        return Vehicle(plate, cylinderCapacity, vehicleType)
    }

    fun bindVehicleMotorcycle(plate: String, cylinderCapacity: Int): Vehicle{
        this.plate = plate
        this.cylinderCapacity = cylinderCapacity
        this.vehicleType = "MOTOCICLETA"
        return Vehicle(plate, cylinderCapacity, vehicleType)
    }

}