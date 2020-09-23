package com.ceiba.adn_csh.dominio.servicios.alquiler.cadena_excepcion_negocio

import com.ceiba.adn_csh.dominio.excepciones.ExcepcionNegocio
import com.ceiba.adn_csh.dominio.modelo.Alquiler

class VehiculoAlquilado(val existeVehiculo: Boolean, val espacioParqueadero: CadenaCrearAlquiler): CadenaCrearAlquiler {

    override fun validar(alquiler: Alquiler): Boolean {
        if(!existeVehiculo){
            return espacioParqueadero.validar(alquiler)
        }
        throw ExcepcionNegocio("El vehiculo ya se encuentra alquilado.")
    }
}