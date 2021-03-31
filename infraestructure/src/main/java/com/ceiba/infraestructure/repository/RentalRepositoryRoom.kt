package com.ceiba.infraestructure.repository

import com.ceiba.domain.model.Rental
import com.ceiba.domain.repository.RentalRepository
import com.ceiba.infraestructure.db.dao.RentalDao
import com.ceiba.infraestructure.db.entity.RentalEntity
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import javax.inject.Singleton
import javax.inject.Inject

@Singleton
class RentalRepositoryRoom @Inject constructor(private val rentalDao: RentalDao) : RentalRepository {

    override fun createRental(rental: Rental): Long = runBlocking {
        val rentalEntity = RentalEntity(id = rental.id, vehicle = rental.vehicle, arrivalDate = rental.arrivalDate, departureDate = rental.departureDate,price = rental.price, active = rental.active)
        val idRental = rentalDao.insert(rentalEntity)
        if(rentalDao.insert(rentalEntity) == 0L) {
            throw Exception()
        }
        return@runBlocking idRental
    }

    override fun rentedVehicle(plate: String): Boolean {
        return rentalDao.rentedVehicle(plate)
    }

    override fun getQuantityOfRentedVehiclesByType(vehicleType: String): Int {
        return rentalDao.getQuantityOfRentedVehiclesByType(vehicleType)
    }

    override fun getActiveRentals(): List<Rental>{
        return rentalDao.getActiveRentals().map { rental ->
            rental.convertRentalEntityToRentalDTO()
        }
    }

    override fun getRental(idRental: Long): Rental {
        return rentalDao.getRental(idRental).convertRentalEntityToRentalDTO()
    }

    override fun updateRentalMakePayment(rental: Rental) = runBlocking {
        val rentalEntity = RentalEntity(id = rental.id, vehicle = rental.vehicle, arrivalDate = rental.arrivalDate,
            departureDate = rental.departureDate,price = rental.price, active = rental.active)
        rentalDao.updateRentalMakePayment(rentalEntity)
    }
}