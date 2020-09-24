package com.ceiba.adn_csh.infraestructure.repositoryImpl

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.ceiba.adn_csh.dominio.exception.BusinessException
import com.ceiba.adn_csh.dominio.model.Rental
import com.ceiba.adn_csh.dominio.repository.RentalRepository
import com.ceiba.adn_csh.infraestructure.db.dao.RentalDao
import kotlinx.coroutines.runBlocking
import javax.inject.Singleton
import javax.inject.Inject

@Singleton
class RentalRepositoryImpl @Inject constructor(rentalDao: RentalDao) : RentalRepository {

    private val rentalDao: RentalDao

    init {
        this.rentalDao = rentalDao
    }

    override fun createRental(rental: Rental) = runBlocking {
        val rentalEntity = rental.convertRentalToRentalEntity()
        if(rentalDao.insert(rentalEntity) == 0L) {
            throw BusinessException("El alquiler no se pudo agregar, intentalo mas tarde.")
        }
    }

    override fun rentedVehicle(plate: String): Boolean {
        return rentalDao.rentedVehicle(plate)
    }

    override fun getQuantityOfRentedVehiclesByType(vehicleType: String): Int {
        return rentalDao.getQuantityOfRentedVehiclesByType(vehicleType)
    }

    override fun getActiveRentals(): LiveData<List<Rental>> {
        return Transformations.map(rentalDao.getActiveRentals()) { rentalListEntity ->
            rentalListEntity.map { rentalEntity ->
                rentalEntity.convertRentalEntityToRentalDTO()
            }
        }
    }

    override fun getActiveRentalById(id: Long): LiveData<Rental> {
        return Transformations.map(rentalDao.getActiveRentalById(id)) { rentalEntity ->
            rentalEntity.convertRentalEntityToRentalDTO()
        }
    }

    override fun updateRentalMakePayment(rental: Rental) = runBlocking {
        val rentalEntity = rental.convertRentalToRentalEntity()
        rentalDao.updateRentalMakePayment(rentalEntity)
    }
}