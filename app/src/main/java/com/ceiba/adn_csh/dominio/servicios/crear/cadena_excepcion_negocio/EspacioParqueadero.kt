package com.ceiba.adn_csh.dominio.servicios.crear.cadena_excepcion_negocio

import com.ceiba.adn_csh.dominio.modelo.Alquiler
import com.ceiba.adn_csh.utilidades.Constantes

class EspacioParqueadero(val cantidadVehiculos: Int, val formatoPlaca: CadenaCrearAlquiler): CadenaCrearAlquiler {

    private var vehiculosCantidades: Map<String, Int>

    init {
        vehiculosCantidades = mapOf(
            Constantes.AUTOMOVIL to Constantes.ESPACIO_MAXIMO_PARA_AUTOMOVILES,
            Constantes.MOTOCICLETA to Constantes.ESPACIO_MAXIMO_PARA_MOTOCICLETAS)
    }

    override fun validar(alquiler: Alquiler): String {
        for((key, value) in vehiculosCantidades){
            if(key == alquiler.vehiculo!!.tipoVehiculo!!.toUpperCase() && cantidadVehiculos < value){
                return formatoPlaca.validar(alquiler)
            }
        }
        return "Lo sentimos, el parqueadero no tiene espacio para ${alquiler.vehiculo!!.tipoVehiculo!!}."
    }

}