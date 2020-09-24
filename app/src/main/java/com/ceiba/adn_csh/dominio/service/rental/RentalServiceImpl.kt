package com.ceiba.adn_csh.dominio.service.rental

import android.util.Log
import androidx.lifecycle.LiveData
import com.ceiba.adn_csh.dominio.model.Rental
import com.ceiba.adn_csh.dominio.repository.RentalRepository
import com.ceiba.adn_csh.dominio.service.rental.business_responsability_chain.calculate_price_vehicle_chain.CalculateBasePrice
import com.ceiba.adn_csh.dominio.service.rental.business_responsability_chain.calculate_price_vehicle_chain.CalculatePricePerCylinder
import com.ceiba.adn_csh.dominio.service.rental.business_responsability_chain.create_rental_chain.ParkingSpace
import com.ceiba.adn_csh.dominio.service.rental.business_responsability_chain.create_rental_chain.PlateFormat
import com.ceiba.adn_csh.dominio.service.rental.business_responsability_chain.create_rental_chain.FirstPlateLetter
import com.ceiba.adn_csh.dominio.service.rental.business_responsability_chain.create_rental_chain.RentedVehicle
import com.ceiba.adn_csh.utilidades.Constantes
import java.lang.Exception
import java.util.*
import javax.inject.Inject

class RentalServiceImpl @Inject constructor(rentalRepository: RentalRepository): RentalService {

    private var rentalRepository: RentalRepository
    private var valuePerDayVehicle: Map<String, Double>
    private var valuePerHourVehicle: Map<String, Double>
    private var valuePerCylinderCapacityVehicle: Map<String, Double>

    init {
        this.rentalRepository = rentalRepository

        valuePerDayVehicle = mapOf(
            Constantes.CAR to Constantes.VALUE_PER_DAY_CAR,
            Constantes.MOTORCYCLE to Constantes.VALUE_PER_DAY_MOTORCYCLE)

        valuePerHourVehicle = mapOf(
            Constantes.CAR to Constantes.VALUE_PER_HOUR_CAR,
            Constantes.MOTORCYCLE to Constantes.VALUE_PER_HOUR_MOTORCYCLE)

        valuePerCylinderCapacityVehicle = mapOf(
            Constantes.CAR to Constantes.VALUE_PER_CYLINDERCAPACITY_CAR,
            Constantes.MOTORCYCLE to Constantes.VALUE_PER_CYLINDERCAPACITY_MOTORCYCLE)
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

    override fun getActiveRentalById(id: Long): LiveData<Rental> {
        return rentalRepository.getActiveRentalById(id)
    }

    override fun updateRentalMakePayment(rental: Rental) {
        try{
            rental.price = calculateVehiclePrice(rental)
            rental.departureDate = Date()
            rental.active = false
            rentalRepository.updateRentalMakePayment(rental)
        }catch (error: Exception){
            Log.e("Service-UpdateRental", "", error)
            throw error
        }
    }

    override fun calculateVehiclePrice(rental: Rental): Double {
        val calculatePricePerCylinder = CalculatePricePerCylinder()
        val calculateBasePrice = CalculateBasePrice(calculatePricePerCylinder)
        return calculateBasePrice.addValue(rental)
    }
}