package com.ceiba.adn_csh.dominio.servicios.alquiler.cadena_excepcion_negocio

import com.ceiba.adn_csh.dominio.modelo.Alquiler

interface CadenaCrearAlquiler{

    fun validar(alquiler: Alquiler): Boolean

}