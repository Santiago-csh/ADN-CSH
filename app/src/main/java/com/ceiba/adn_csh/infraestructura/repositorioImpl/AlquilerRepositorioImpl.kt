package com.ceiba.adn_csh.infraestructura.repositorioImpl

import com.ceiba.adn_csh.dominio.modelo.Alquiler
import com.ceiba.adn_csh.dominio.repositorio.AlquilerRepositorio
import com.ceiba.adn_csh.infraestructura.dao.AlquilerDao
import com.ceiba.adn_csh.infraestructura.entidades.AlquilerEntidad
import javax.inject.Singleton
import javax.inject.Inject

@Singleton
class AlquilerRepositorioImpl @Inject constructor(alquilerDao: AlquilerDao) : AlquilerRepositorio {

    private val alquilerDao: AlquilerDao

    init {
        this.alquilerDao = alquilerDao
    }

    override fun crearAlquiler(alquiler: Alquiler): Boolean {
        TODO("Not yet implemented")
    }

    override fun estaAlquilado(): Boolean {
        TODO("Not yet implemented")
    }

    override fun obtenerCantidadVehiculosAlquiladosPorTipo(tipoVehiculo: String): Int {
        return alquilerDao.obtenerCantidadVehiculosAlquiladosPorTipo(tipoVehiculo)
    }

    fun convertirAEntidad(alquiler: Alquiler): AlquilerEntidad{
        val alquilerEntidad = AlquilerEntidad()
        alquilerEntidad.vehiculo = alquiler.vehiculo
        alquilerEntidad.horaLlegada = alquiler.horaLlegada
        return alquilerEntidad
    }

}