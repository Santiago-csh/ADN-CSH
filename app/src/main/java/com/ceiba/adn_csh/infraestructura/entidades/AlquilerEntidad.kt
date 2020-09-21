package com.ceiba.adn_csh.infraestructura.entidades

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ceiba.adn_csh.dominio.modelo.Vehiculo
import java.util.*

@Entity(tableName = "alquiler")
class AlquilerEntidad(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @Embedded(prefix = "vehiculo_")
    var vehiculo: Vehiculo? = null,

    var horaLlegada: Date? = null,

    var horaSalida: Date? = null,

    var precio: Double = 0.0,

    var activo: Boolean = true
){}