package com.ceiba.adn_csh.dominio.service.rental

import android.util.Log
import androidx.lifecycle.LiveData
import com.ceiba.adn_csh.dominio.model.Rental
import com.ceiba.adn_csh.dominio.repository.RentalRepository
import com.ceiba.adn_csh.dominio.service.rental.business_exception_chain.ParkingSpace
import com.ceiba.adn_csh.dominio.service.rental.business_exception_chain.PlateFormat
import com.ceiba.adn_csh.dominio.service.rental.business_exception_chain.FirstPlateLetter
import com.ceiba.adn_csh.dominio.service.rental.business_exception_chain.RentedVehicle
import java.lang.Exception
import javax.inject.Inject

class RentalServiceImpl @Inject constructor(rentalRepository: RentalRepository): RentalService {

    private var rentalRepository: RentalRepository

    init {
        this.rentalRepository = rentalRepository
    }

    override fun createRental(rental: Rental) {
        try {
            val vehicleExists = rentalRepository.rentedVehicle(rental.vehicle!!.plate!!)
            val quantity = rentalRepository.getQuantityOfRentedVehiclesByType(rental.vehicle!!.vehicleType!!)
            val firstPlateLetter = FirstPlateLetter()
            val plateFormat = PlateFormat(firstPlateLetter)
            val parkingSpace = ParkingSpace(quantity, plateFormat)
            val vehiculoAlquilado = RentedVehicle(vehicleExists, parkingSpace)
            vehiculoAlquilado.validate(rental)
            rentalRepository.createRental(rental)
        }catch (error: Exception){
            Log.e("Service-CreateRental", "", error)
            throw error
        }
    }

    override fun getActiveRentals(): LiveData<List<Rental>> {
        return rentalRepository.getActiveRentals()
    }
}