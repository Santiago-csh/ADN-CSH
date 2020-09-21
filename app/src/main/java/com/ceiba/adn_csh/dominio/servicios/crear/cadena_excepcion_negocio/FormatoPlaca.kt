package com.ceiba.adn_csh.dominio.servicios.crear.cadena_excepcion_negocio

import com.ceiba.adn_csh.dominio.modelo.Alquiler
import com.ceiba.adn_csh.utilidades.Constantes
import java.util.regex.Pattern

class FormatoPlaca(val primeraLetraPlaca: CadenaCrearAlquiler): CadenaCrearAlquiler {

    private var vehiculosFormatoPlacas: Map<String, String>

    init {
        vehiculosFormatoPlacas = mapOf(
            Constantes.AUTOMOVIL to Constantes.EXPRESION_REGULAR_AUTOMOVILES,
            Constantes.MOTOCICLETA to Constantes.EXPRESION_REGULAR_MOTOCICLETAS)
    }

    override fun validar(alquiler: Alquiler): String {
        for((key, value) in vehiculosFormatoPlacas){
            if(key == alquiler.vehiculo!!.tipoVehiculo!!.toUpperCase()){
                val patron = Pattern.compile(value)
                if(patron.matcher(alquiler.vehiculo!!.placa!!).matches()){
                    return primeraLetraPlaca.validar(alquiler)
                }
            }
        }
        return "La placa del ${alquiler.vehiculo!!.placa!!.toLowerCase()} no es valida."
    }

}