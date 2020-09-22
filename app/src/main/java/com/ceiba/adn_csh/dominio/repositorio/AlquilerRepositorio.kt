package com.ceiba.adn_csh.dominio.repositorio

import com.ceiba.adn_csh.dominio.modelo.Alquiler

interface AlquilerRepositorio {

    fun crearAlquiler(alquiler: Alquiler): Boolean
    fun vehiculoAlquilado(placa: String): Boolean
    fun obtenerCantidadVehiculosAlquiladosPorTipo(tipoVehiculo: String): Int
    fun obtenerTotalAlquileres(): Int

}