package com.ceiba.infraestructure.db.dao

import androidx.room.*
import com.ceiba.infraestructure.db.entity.RentalEntity
import io.reactivex.Observable

@Dao interface RentalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rentalEntity: RentalEntity): Long

    @Update
    suspend fun updateRentalMakePayment(rentalEntity: RentalEntity)

    @Query("SELECT EXISTS (SELECT * FROM rental r WHERE r.vehicle_plate LIKE :plate AND r.active = 1 LIMIT 1)")
    fun rentedVehicle(plate: String): Boolean

    @Query("SELECT COUNT(*) FROM rental r WHERE r.vehicle_vehicleType LIKE :vehicleType AND r.active = 1")
    fun getQuantityOfRentedVehiclesByType(vehicleType: String): Int

    @Query("SELECT * FROM rental r WHERE r.active = 1")
    fun getActiveRentals(): Observable<List<RentalEntity>>

}