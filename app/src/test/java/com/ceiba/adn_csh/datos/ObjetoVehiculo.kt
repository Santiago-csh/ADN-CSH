package com.ceiba.adn_csh.datos

import com.ceiba.adn_csh.dominio.modelo.Vehiculo

class ObjetoVehiculo {

    var placa: String? = null
    var cilindraje: Int = 0
    var tipoVehiculo: String? = null

    init {
        this.placa = "LSD528"
        this.cilindraje = 200
        this.tipoVehiculo = ""
    }

    fun crearAutomovilPlacaBuena(): Vehiculo{
        this.placa = "EES999"
        this.tipoVehiculo = "AUTOMOVIL"
        return Vehiculo(placa!!, cilindraje, tipoVehiculo!!)
    }

    fun crearAutomovilPlacaMala(): Vehiculo{
        this.placa = "I5S8T"
        this.tipoVehiculo = "AUTOMOVIL"
        return Vehiculo(placa!!, cilindraje, tipoVehiculo!!)
    }

    fun crearAutomovilValidacionPrimeraLetraPlaca(): Vehiculo{
        this.placa = "ACJ192"
        this.tipoVehiculo = "AUTOMOVIL"
        return Vehiculo(placa!!, cilindraje, tipoVehiculo!!)
    }

    fun crearMotocicletaPlacaBuena(): Vehiculo{
        this.placa = "DCF15B"
        this.tipoVehiculo = "MOTOCICLETA"
        return Vehiculo(placa!!, cilindraje, tipoVehiculo!!)
    }

    fun crearMotocicletaPlacaMala(): Vehiculo{
        this.placa = "IYG855"
        this.tipoVehiculo = "MOTOCICLETA"
        return Vehiculo(placa!!, cilindraje, tipoVehiculo!!)
    }

}