package com.ceiba.domain.service

import com.ceiba.domain.model.Rental
import com.ceiba.domain.repository.RentalRepository
import com.ceiba.domain.service.businesscomposite.calculatepricevehicle.CalculatePriceComposite
import com.ceiba.domain.service.businesscomposite.calculatepricevehicle.PricePerTime
import com.ceiba.domain.service.businesscomposite.calculatepricevehicle.PricePerCylinder
import com.ceiba.domain.service.businesscomposite.validationscreaterental.CreateRentalComposite
import com.ceiba.domain.service.businesscomposite.validationscreaterental.ParkingSpace
import com.ceiba.domain.service.businesscomposite.validationscreaterental.RentedVehicleLeaf
import java.util.*
import javax.inject.Inject

class RentalServiceImpl @Inject constructor(private var rentalRepository: RentalRepository): RentalService {

    override fun createRental(rental: Rental): Long {
        val vehicleExists = rentalRepository.rentedVehicle(rental.vehicle.plate)
        val quantityOfRentedVehicles = rentalRepository.getQuantityOfRentedVehiclesByType(rental.vehicle.vehicleType)
        val createRentalComposite = CreateRentalComposite()
        createRentalComposite.add(ParkingSpace(quantityOfRentedVehicles))
        createRentalComposite.add(RentedVehicleLeaf(vehicleExists))
        createRentalComposite.execute(rental)
        return rentalRepository.createRental(rental)
    }

    override fun updateRentalMakePayment(rental: Rental): Rental {
        rental.departureDate = Date()
        rental.price = calculateVehiclePrice(rental)
        rental.active = false
        rentalRepository.updateRentalMakePayment(rental)
        return rental
    }

    override fun getActiveRentals(): List<Rental> {
        return rentalRepository.getActiveRentals()
    }

    override fun getRental(idRental: Long): Rental {
        return rentalRepository.getRental(idRental)
    }

    fun calculateVehiclePrice(rental: Rental): Double {
        val calculatePriceComposite = CalculatePriceComposite()
        calculatePriceComposite.add(PricePerCylinder())
        calculatePriceComposite.add(PricePerTime())
        return calculatePriceComposite.execute(rental)
    }
}