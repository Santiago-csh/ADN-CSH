package com.ceiba.adn_csh.infraestructura.repositorioImpl

import com.ceiba.adn_csh.dominio.excepciones.ExcepcionNegocio
import com.ceiba.adn_csh.dominio.modelo.Alquiler
import com.ceiba.adn_csh.dominio.repositorio.AlquilerRepositorio
import com.ceiba.adn_csh.infraestructura.db.dao.AlquilerDao
import com.ceiba.adn_csh.infraestructura.db.entidades.AlquilerEntidad
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import java.util.*
import javax.inject.Singleton
import javax.inject.Inject

@Singleton
class AlquilerRepositorioImpl @Inject constructor(alquilerDao: AlquilerDao) : AlquilerRepositorio {

    private val alquilerDao: AlquilerDao

    init {
        this.alquilerDao = alquilerDao
    }

    override fun crearAlquiler(alquiler: Alquiler) = runBlocking {
        val alquilerEntidad = AlquilerEntidad()
        alquilerEntidad.vehiculo = alquiler.vehiculo
        alquilerEntidad.fechaLlegada = Date()
        if(alquilerDao.insert(alquilerEntidad) == 0L) {
            throw ExcepcionNegocio("El alquiler no se pudo agregar, intentalo mas tarde.")
        }
    }

    override fun vehiculoAlquilado(placa: String): Boolean {
        return alquilerDao.vehiculoAlquilado(placa)
    }

    override fun obtenerCantidadVehiculosAlquiladosPorTipo(tipoVehiculo: String): Int {
        return alquilerDao.obtenerCantidadVehiculosAlquiladosPorTipo(tipoVehiculo)
    }

    override fun obtenerTotalAlquileres(): Int {
        return alquilerDao.obtenerTotalAlquileres()
    }

}