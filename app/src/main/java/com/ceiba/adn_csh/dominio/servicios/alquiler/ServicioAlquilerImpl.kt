package com.ceiba.adn_csh.dominio.servicios.alquiler

import android.util.Log
import com.ceiba.adn_csh.dominio.excepciones.ExcepcionNegocio
import com.ceiba.adn_csh.dominio.modelo.Alquiler
import com.ceiba.adn_csh.dominio.repositorio.AlquilerRepositorio
import com.ceiba.adn_csh.dominio.servicios.alquiler.cadena_excepcion_negocio.EspacioParqueadero
import com.ceiba.adn_csh.dominio.servicios.alquiler.cadena_excepcion_negocio.FormatoPlaca
import com.ceiba.adn_csh.dominio.servicios.alquiler.cadena_excepcion_negocio.PrimeraLetraPlaca
import com.ceiba.adn_csh.dominio.servicios.alquiler.cadena_excepcion_negocio.VehiculoAlquilado
import java.lang.Exception
import javax.inject.Inject

class ServicioAlquilerImpl @Inject constructor(alquilerRepositorio: AlquilerRepositorio): ServicioAlquiler {

    private var alquilerRepositorio: AlquilerRepositorio

    init {
        this.alquilerRepositorio = alquilerRepositorio
    }

    override fun crearAlquiler(alquiler: Alquiler) {
        try {
            val existeVehiculo = alquilerRepositorio.vehiculoAlquilado(alquiler.vehiculo!!.placa!!)
            val cantidad = alquilerRepositorio.obtenerCantidadVehiculosAlquiladosPorTipo(alquiler.vehiculo!!.tipoVehiculo!!)
            val primeraLetraPlaca = PrimeraLetraPlaca()
            val formatoPlaca = FormatoPlaca(primeraLetraPlaca)
            val espacioParqueadero = EspacioParqueadero(cantidad, formatoPlaca)
            val vehiculoAlquilado = VehiculoAlquilado(existeVehiculo, espacioParqueadero)
            vehiculoAlquilado.validar(alquiler)
            alquilerRepositorio.crearAlquiler(alquiler)
        }catch (error: Exception){
            Log.e("ServicioCrearAlquiler", "", error)
            throw error
        }
    }
}