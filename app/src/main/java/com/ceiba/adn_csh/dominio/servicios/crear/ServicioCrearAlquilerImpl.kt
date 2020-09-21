package com.ceiba.adn_csh.dominio.servicios.crear

import com.ceiba.adn_csh.dominio.excepciones.ExcepcionNegocio
import com.ceiba.adn_csh.dominio.modelo.Alquiler
import com.ceiba.adn_csh.dominio.repositorio.AlquilerRepositorio
import com.ceiba.adn_csh.dominio.servicios.crear.cadena_excepcion_negocio.EspacioParqueadero
import com.ceiba.adn_csh.dominio.servicios.crear.cadena_excepcion_negocio.FormatoPlaca
import com.ceiba.adn_csh.dominio.servicios.crear.cadena_excepcion_negocio.PrimeraLetraPlaca
import javax.inject.Inject

class ServicioCrearAlquilerImpl @Inject constructor(alquilerRepositorio: AlquilerRepositorio): ServicioCrearAlquiler {

    private var alquilerRepositorio: AlquilerRepositorio

    init {
        this.alquilerRepositorio = alquilerRepositorio
    }

    override fun crearAlquiler(alquiler: Alquiler): Boolean {
        val cantidad = alquilerRepositorio.obtenerCantidadVehiculosAlquiladosPorTipo(alquiler.vehiculo!!.tipoVehiculo!!)
        val mensaje: String = EspacioParqueadero(cantidad, FormatoPlaca(PrimeraLetraPlaca())).validar(alquiler)
        if(mensaje == "true"){
            return alquilerRepositorio.crearAlquiler(alquiler)
        }else{
            throw ExcepcionNegocio(mensaje)
        }
    }
}