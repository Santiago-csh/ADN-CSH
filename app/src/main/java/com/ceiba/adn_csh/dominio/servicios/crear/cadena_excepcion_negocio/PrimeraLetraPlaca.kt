package com.ceiba.adn_csh.dominio.servicios.crear.cadena_excepcion_negocio

import com.ceiba.adn_csh.dominio.modelo.Alquiler
import java.util.*

class PrimeraLetraPlaca: CadenaCrearAlquiler {

    override fun validar(alquiler: Alquiler): String {
        if(alquiler.vehiculo!!.placa!![0].toUpperCase() == 'A'){
            val diaActual = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
            if(diaActual != Calendar.MONDAY && diaActual != Calendar.SUNDAY){
                return "No esta autorizado tu ingreso."
            }
        }
        return "true"
    }

}