package com.ceiba.adn_csh.dominio.modelo

open class Vehiculo(placa: String, cilindraje: Int, tipoVehiculo: String) {

    var placa: String? = null
    var cilindraje: Int = 0
    var tipoVehiculo: String? = null

    init {
        this.placa = placa
        this.cilindraje = cilindraje
        this.tipoVehiculo = tipoVehiculo
    }

}