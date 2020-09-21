package com.ceiba.adn_csh.dominio.servicios.crear

import com.ceiba.adn_csh.dominio.modelo.Alquiler

interface ServicioCrearAlquiler {

    fun crearAlquiler(alquiler: Alquiler): Boolean

}