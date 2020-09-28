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


    fun createRentCarPlateGood(): Rental {
        vehicle = VehicleBuilder().createCarPlateGood()
        return bindSimpleRental()
    }

    fun createRentCarPlateBad(): Rental {
        vehicle = VehicleBuilder().createCarPlateBad()
        return bindSimpleRental()
    }

    fun createRentMotorcyclePlateGood(): Rental {
        vehicle = VehicleBuilder().createMotorcyclePlateGood()
        return bindSimpleRental()
    }

    fun createRentMotorcyclePlateBad(): Rental {
        vehicle = VehicleBuilder().createMotorcyclePlateBad()
        return bindSimpleRental()
    }

    fun createRentCarValidationFirstLetter(): Rental {
        vehicle = VehicleBuilder().createCarValidationFirstLetter()
        return bindSimpleRental()
    }

    fun createRentCarWithHoursAndMinutes(): Rental {
        vehicle = VehicleBuilder().createCarPlateGood()
        return bindRentalWithModifiedDepartureDateHoursAndMinutes(0,15)
    }

    fun createRentCarWithHoursAndWithoutDays(): Rental {
        vehicle = VehicleBuilder().createCarPlateGood()
        return bindRentalWithModifiedDepartureDateHoursOrDays(6)
    }

    fun createRentCarWithDaysAndWithoutHours(): Rental {
        vehicle = VehicleBuilder().createCarPlateGood()
        return bindRentalWithModifiedDepartureDateHoursOrDays(33)
    }

    fun createRentCarWithDaysAndHours(): Rental {
        vehicle = VehicleBuilder().createCarPlateGood()
        return bindRentalWithModifiedDepartureDateHoursOrDays(31)
    }

    fun createRentMotorcycleCylinderCapacityOfGreaterThanFiveHundredWithHoursAndMinutes(): Rental {
        vehicle = VehicleBuilder().createMotorcycleCylinderCapacityGreaterThanFiveHundred()
        return bindRentalWithModifiedDepartureDateHoursAndMinutes(0,20)
    }

    fun createRentMotorcycleCylinderCapacityLessOrEqualsThanFiveHundredWithHoursAndWithoutDays(): Rental {
        vehicle = VehicleBuilder().createMotorcycleCylinderCapacityLessOrEqualsThanFiveHundred()
        return bindRentalWithModifiedDepartureDateHoursOrDays(8)
    }

    fun createRentMotorcycleCylinderCapacityGreaterThanFiveHundredWithDaysAndWithoutHours(): Rental {
        vehicle = VehicleBuilder().createMotorcycleCylinderCapacityGreaterThanFiveHundred()
        return bindRentalWithModifiedDepartureDateHoursOrDays(85)
    }

    fun createRentMotorcycleCylinderCapacityGreaterThanFiveHundredWithDaysAndHours(): Rental {
        vehicle = VehicleBuilder().createMotorcycleCylinderCapacityGreaterThanFiveHundred()
        return bindRentalWithModifiedDepartureDateHoursOrDays(127)
    }

    fun bindSimpleRental(): Rental{
        return Rental(id, vehicle, arrivalDate, departureDate, price, active)
    }

    fun bindRentalWithModifiedDepartureDateHoursOrDays(valueHour: Int): Rental{
        calendar[Calendar.HOUR] = calendar[Calendar.HOUR] + valueHour
        return Rental(id, vehicle, arrivalDate, calendar.time, price, active)
    }

    fun bindRentalWithModifiedDepartureDateHoursAndMinutes(valueHour: Int, valueMinutes: Int): Rental{
        calendar[Calendar.HOUR] = calendar[Calendar.HOUR] + valueHour
        calendar[Calendar.MINUTE] = calendar[Calendar.MINUTE] + valueMinutes
        return Rental(id, vehicle, arrivalDate, calendar.time, price, active)
    }
}