package com.ceiba.adn_csh.infraestructura.view_model.factoria

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ceiba.adn_csh.dominio.servicios.alquiler.ServicioAlquiler
import com.ceiba.adn_csh.infraestructura.view_model.AlquilerViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider

import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: throw IllegalArgumentException("Unknown model class $modelClass")
        return creator.get() as T
    }
}