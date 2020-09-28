package com.ceiba.infraestructure.repositoryImpl

import com.ceiba.domain.model.Rental
import com.ceiba.adn_csh.domain.repository.RentalRepository
import com.ceiba.infraestructure.db.dao.RentalDao
import com.ceiba.infraestructure.db.entity.RentalEntity
import io.reactivex.Observable
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import javax.inject.Singleton
import javax.inject.Inject

@Singleton
class RentalRepositoryImpl @Inject constructor(private val rentalDao: RentalDao) : RentalRepository {

    override fun createRental(rental: Rental) = runBlocking {
        val rentalEntity = RentalEntity(id = rental.id, vehicle = rental.vehicle, arrivalDate = rental.arrivalDate, departureDate = rental.departureDate,price = rental.price, active = rental.active)
        if(rentalDao.insert(rentalEntity) == 0L) {
            throw Exception()
        }
    }

    override fun rentedVehicle(plate: String): Boolean {
        return rentalDao.rentedVehicle(plate)
    }

    override fun getQuantityOfRentedVehiclesByType(vehicleType: String): Int {
        return rentalDao.getQuantityOfRentedVehiclesByType(vehicleType)
    }

    override fun getActiveRentals(): Observable<List<Rental>>{
        return rentalDao.getActiveRentals().map { rentalList ->
            rentalList.map { rental ->
                rental.convertRentalEntityToRentalDTO()
            }
        }
    }

    override fun updateRentalMakePayment(rental: Rental) = runBlocking {
        val rentalEntity = RentalEntity(id = rental.id, vehicle = rental.vehicle, arrivalDate = rental.arrivalDate, departureDate = rental.departureDate,price = rental.price, active = rental.active)
        rentalDao.updateRentalMakePayment(rentalEntity)
    }
}