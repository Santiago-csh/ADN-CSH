package com.ceiba.adn_csh.infraestructura.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ceiba.adn_csh.dominio.modelo.Alquiler
import com.ceiba.adn_csh.infraestructura.entidades.AlquilerEntidad

@Dao interface AlquilerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(alquilerEntidad: AlquilerEntidad): Long

    @Query("SELECT EXISTS (SELECT * FROM alquiler a WHERE a.vehiculo_placa LIKE :placa AND a.activo = 1 LIMIT 1)")
    fun vehiculoAlquilado(placa: String): Boolean

    @Query("SELECT COUNT(*) FROM alquiler a WHERE vehiculo_tipoVehiculo LIKE :tipoVehiculo AND activo = 1")
    fun obtenerCantidadVehiculosAlquiladosPorTipo(tipoVehiculo: String): Int

    @Query("SELECT COUNT(*) FROM alquiler")
    fun obtenerTotalAlquileres(): Int

}