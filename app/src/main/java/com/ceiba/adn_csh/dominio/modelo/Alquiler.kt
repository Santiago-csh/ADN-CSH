package com.ceiba.adn_csh.dominio.modelo

import java.util.*

class Alquiler(
    var id: Long = 0,
    var vehiculo: Vehiculo? = null,
    var fechaLlegada: Date? = null,
    var fechaSalida: Date? = null,
    var precio: Double = 0.0,
    var activo: Boolean = true)