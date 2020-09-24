package com.ceiba.adn_csh.infraestructure.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ceiba.adn_csh.infraestructure.db.entity.RentalEntity

@Dao interface RentalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rentalEntity: RentalEntity): Long

    @Query("SELECT EXISTS (SELECT * FROM rental r WHERE r.vehicle_plate LIKE :plate AND r.active = 1 LIMIT 1)")
    fun rentedVehicle(plate: String): Boolean

    @Query("SELECT COUNT(*) FROM rental r WHERE r.vehicle_vehicleType LIKE :vehicleType AND r.active = 1")
    fun getQuantityOfRentedVehiclesByType(vehicleType: String): Int

    @Query("SELECT * FROM rental r WHERE r.active = 1")
    fun getActiveRentals(): LiveData<List<RentalEntity>>

    @Query("SELECT * FROM rental r WHERE r.id = :id")
    fun getActiveRentalById(id: Long): LiveData<RentalEntity>

    @Update
    suspend fun updateRentalMakePayment(rentalEntity: RentalEntity)

}