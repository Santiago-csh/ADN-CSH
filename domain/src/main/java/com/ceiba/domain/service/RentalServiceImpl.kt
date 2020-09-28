package com.ceiba.domain.service

import com.ceiba.domain.model.Rental
import com.ceiba.adn_csh.domain.repository.RentalRepository
import com.ceiba.domain.service.businessresponsabilitychain.calculatepricevehicle.PricePerTime
import com.ceiba.domain.service.businessresponsabilitychain.calculatepricevehicle.PricePerCylinder
import com.ceiba.domain.service.businessresponsabilitychain.validationscreaterental.ParkingSpace
import com.ceiba.domain.service.businessresponsabilitychain.validationscreaterental.RentedVehicle
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class RentalServiceImpl @Inject constructor(private var rentalRepository: RentalRepository): RentalService {

    override fun createRental(rental: Rental) {
        val vehicleExists = rentalRepository.rentedVehicle(rental.vehicle.plate)
        val quantity = rentalRepository.getQuantityOfRentedVehiclesByType(rental.vehicle.vehicleType)
        val parkingSpace = ParkingSpace(quantity)
        val rentedVehicle = RentedVehicle(vehicleExists, parkingSpace)
        rentedVehicle.validation(rental)
        rentalRepository.createRental(rental)
    }

    override fun updateRentalMakePayment(rental: Rental): Rental {
        rental.departureDate = Date()
        rental.price = calculateVehiclePrice(rental)
        rental.active = false
        rentalRepository.updateRentalMakePayment(rental)
        return rental
    }


    override fun getActiveRentals(): Observable<List<Rental>> {
        return rentalRepository.getActiveRentals()
    }

    fun calculateVehiclePrice(rental: Rental): Double {
        val pricePerCylinder = PricePerCylinder()
        val pricePerTime = PricePerTime(pricePerCylinder)
        return pricePerTime.calculatePrice(rental)
    }
}