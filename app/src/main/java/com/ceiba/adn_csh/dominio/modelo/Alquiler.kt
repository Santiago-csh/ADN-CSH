package com.ceiba.adn_csh.dominio.modelo

import java.util.*

class Alquiler(id: Long, vehiculo: Vehiculo, fechaLlegada: Date, fechaSalida: Date, precio: Double, activo: Boolean) {

    var id: Long = 0
    var vehiculo: Vehiculo? = null
    var fechaLlegada: Date? = null
    var fechaSalida: Date? = null
    var precio: Double = 0.0
    var activo: Boolean

    init {
        this.id = id
        this.vehiculo = vehiculo
        this.fechaLlegada = fechaLlegada
        this.fechaSalida = fechaSalida
        this.precio = precio
        this.activo = activo
    }

}