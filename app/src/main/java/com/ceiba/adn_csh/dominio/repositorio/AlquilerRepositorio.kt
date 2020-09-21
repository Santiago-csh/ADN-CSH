package com.ceiba.adn_csh.dominio.repositorio

import com.ceiba.adn_csh.dominio.modelo.Alquiler

interface AlquilerRepositorio {

    fun crearAlquiler(alquiler: Alquiler): Boolean
    fun estaAlquilado(): Boolean
    fun obtenerCantidadVehiculosAlquiladosPorTipo(tipoVehiculo: String): Int

}