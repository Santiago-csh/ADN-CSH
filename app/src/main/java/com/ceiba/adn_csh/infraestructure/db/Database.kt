package com.ceiba.adn_csh.infraestructure.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ceiba.adn_csh.infraestructure.db.dao.RentalDao
import com.ceiba.adn_csh.infraestructure.db.entidades.RentalEntity

@Database(entities = [RentalEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class Database: RoomDatabase() {
    abstract fun rentalDao(): RentalDao
}