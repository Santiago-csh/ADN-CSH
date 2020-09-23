package com.ceiba.adn_csh.infraestructura.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ceiba.adn_csh.dominio.modelo.Alquiler
import com.ceiba.adn_csh.dominio.servicios.alquiler.ServicioAlquiler
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlquilerViewModel @Inject constructor(servicioAlquiler: ServicioAlquiler): ViewModel() {

    var servicioAlquiler: ServicioAlquiler

    init {
        this.servicioAlquiler = servicioAlquiler
    }

    @Throws(Exception::class)
    fun crearAlquiler(alquiler: Alquiler){
        return servicioAlquiler.crearAlquiler(alquiler)
    }

}