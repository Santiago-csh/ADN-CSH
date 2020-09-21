package com.ceiba.adn_csh.infraestructura.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ceiba.adn_csh.infraestructura.entidades.AlquilerEntidad

@Dao interface AlquilerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(alquilerEntidad: AlquilerEntidad): Long

    @Query("SELECT COUNT(*) FROM alquiler a WHERE vehiculo_tipoVehiculo = :tipoVehiculo AND activo = 1")
    fun obtenerCantidadVehiculosAlquiladosPorTipo(tipoVehiculo: String): Int

}