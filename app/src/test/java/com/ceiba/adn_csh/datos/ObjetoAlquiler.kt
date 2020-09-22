package com.ceiba.adn_csh.datos

import com.ceiba.adn_csh.dominio.modelo.Alquiler
import com.ceiba.adn_csh.dominio.modelo.Vehiculo
import java.text.SimpleDateFormat
import java.util.*

class ObjetoAlquiler {

    var id: Long = 0
    var vehiculo: Vehiculo? = null
    var fechaLLegada: Date? = null
    var fechaSalida: Date? = null

    init {
        val formatoFecha = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")

        id = 1
        fechaLLegada = formatoFecha.parse("21-Sep-2020 06:00:00")
        fechaSalida = formatoFecha.parse("21-Sep-2020 07:00:00")
    }

    fun crearAlquilerAutomovilPlacaBuena(): Alquiler{
        vehiculo = ObjetoVehiculo().crearAutomovilPlacaBuena()
        return Alquiler(id, vehiculo!!, fechaLLegada!!, fechaSalida!!, 0.0,true)
    }

    fun crearAlquilerAutomovilPlacaMala(): Alquiler{
        vehiculo = ObjetoVehiculo().crearAutomovilPlacaMala()
        return Alquiler(id, vehiculo!!, fechaLLegada!!, fechaSalida!!, 0.0,true)
    }

    fun crearAlquilerMotocicletaPlacaBuena(): Alquiler{
        vehiculo = ObjetoVehiculo().crearMotocicletaPlacaBuena()
        return Alquiler(id, vehiculo!!, fechaLLegada!!, fechaSalida!!, 0.0,true)
    }

    fun crearAlquilerMotocicletaPlacaMala(): Alquiler{
        vehiculo = ObjetoVehiculo().crearMotocicletaPlacaMala()
        return Alquiler(id, vehiculo!!, fechaLLegada!!, fechaSalida!!, 0.0,true)
    }

    fun crearAlquilerAutomovilValidacionPrimeraLetra(): Alquiler{
        vehiculo = ObjetoVehiculo().crearAutomovilValidacionPrimeraLetraPlaca()
        return Alquiler(id, vehiculo!!, fechaLLegada!!, fechaSalida!!, 0.0,true)
    }

}