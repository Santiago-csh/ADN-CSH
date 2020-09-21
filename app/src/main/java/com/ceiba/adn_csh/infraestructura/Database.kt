package com.ceiba.adn_csh.infraestructura

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ceiba.adn_csh.infraestructura.dao.AlquilerDao
import com.ceiba.adn_csh.infraestructura.entidades.AlquilerEntidad

@Database(entities = [AlquilerEntidad::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class Database: RoomDatabase() {
    abstract fun alquilerDao(): AlquilerDao
}