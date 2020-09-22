package com.ceiba.adn_csh.dominio.servicios.crear

import android.util.Log
import com.ceiba.adn_csh.dominio.excepciones.ExcepcionNegocio
import com.ceiba.adn_csh.dominio.modelo.Alquiler
import com.ceiba.adn_csh.dominio.repositorio.AlquilerRepositorio
import com.ceiba.adn_csh.dominio.servicios.crear.cadena_excepcion_negocio.EspacioParqueadero
import com.ceiba.adn_csh.dominio.servicios.crear.cadena_excepcion_negocio.FormatoPlaca
import com.ceiba.adn_csh.dominio.servicios.crear.cadena_excepcion_negocio.PrimeraLetraPlaca
import java.lang.Exception
import javax.inject.Inject

class ServicioCrearAlquilerImpl @Inject constructor(alquilerRepositorio: AlquilerRepositorio): ServicioCrearAlquiler {

    private var alquilerRepositorio: AlquilerRepositorio

    init {
        this.alquilerRepositorio = alquilerRepositorio
    }

    override fun crearAlquiler(alquiler: Alquiler): Boolean {
        try {
            if(alquilerRepositorio.vehiculoAlquilado(alquiler.vehiculo!!.placa!!)){
                throw ExcepcionNegocio("El vehiculo ya se encuentra alquilado.")
            }
            val cantidad = alquilerRepositorio.obtenerCantidadVehiculosAlquiladosPorTipo(alquiler.vehiculo!!.tipoVehiculo!!)
            val primeraLetraPlaca = PrimeraLetraPlaca()
            val formatoPlaca = FormatoPlaca(primeraLetraPlaca)
            val espacioParqueadero = EspacioParqueadero(cantidad, formatoPlaca)
            espacioParqueadero.validar(alquiler)
            return alquilerRepositorio.crearAlquiler(alquiler)
        }catch (error: Exception){
            Log.e("ServicioCrearAlquiler", "", error)
            throw error
        }
    }
}