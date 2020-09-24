package com.ceiba.adn_csh.build

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ceiba.adn_csh.dominio.model.Rental
import com.ceiba.adn_csh.dominio.model.Vehicle
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class RentalBuilder {

    var id: Long = 1
    var vehicle: Vehicle? = null
    var arrivalDate: Date = Date()
    var departureDate: Date = Date()
    var price: Double = 0.0
    var active: Boolean = true

    val formatoFecha: SimpleDateFormat
    val calendar: Calendar
    init {
        formatoFecha = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")
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

    fun createRentalList(): LiveData<List<Rental>> {
        val lists = ArrayList<Rental>()

        vehicle = VehicleBuilder().createCarPlateGood()
        var rental = bindSimpleRental()
        lists.add(rental)
        lists.add(rental)
        lists.add(rental)

        vehicle = VehicleBuilder().createMotorcyclePlateGood()
        rental = bindSimpleRental()
        lists.add(rental)
        lists.add(rental)

        return MutableLiveData(lists)
    }

    fun createRentCarWithHoursAndWithoutDays(): Rental {
        vehicle = VehicleBuilder().createCarPlateGood()
        return bindRentalWithModifiedDepartureDate(6)
    }

    fun createRentCarWithDaysAndWithoutHours(): Rental {
        vehicle = VehicleBuilder().createCarPlateGood()
        return bindRentalWithModifiedDepartureDate(33)
    }

    fun createRentCarWithDaysAndHours(): Rental {
        vehicle = VehicleBuilder().createCarPlateGood()
        return bindRentalWithModifiedDepartureDate(31)
    }

    fun createRentMotorcycleCylinderCapacityLessOrEqualsThanFiveHundredWithHoursAndWithoutDays(): Rental {
        vehicle = VehicleBuilder().createMotorcycleCylinderCapacityLessOrEqualsThanFiveHundred()
        return bindRentalWithModifiedDepartureDate(8)
    }

    fun createRentMotorcycleCylinderCapacityGreaterThanFiveHundredWithDaysAndWithoutHours(): Rental {
        vehicle = VehicleBuilder().createMotorcycleCylinderCapacityGreaterThanFiveHundred()
        return bindRentalWithModifiedDepartureDate(85)
    }

    fun createRentMotorcycleCylinderCapacityGreaterThanFiveHundredWithDaysAndHours(): Rental {
        vehicle = VehicleBuilder().createMotorcycleCylinderCapacityGreaterThanFiveHundred()
        return bindRentalWithModifiedDepartureDate(127)
    }

    fun bindSimpleRental(): Rental{
        return Rental(id, vehicle!!, arrivalDate, departureDate, price, active)
    }

    fun bindRentalWithModifiedDepartureDate(valueHour: Int): Rental{
        calendar[Calendar.HOUR] = calendar[Calendar.HOUR] + valueHour
        return Rental(id, vehicle!!, arrivalDate, calendar.time, price, active)
    }
}