package com.ceiba.adn_csh.dominio.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ceiba.adn_csh.infraestructura.view_model.AlquilerViewModel
import com.ceiba.adn_csh.infraestructura.view_model.factoria.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModulo {

    @Binds
    @IntoMap
    @ViewModelKey(AlquilerViewModel::class)
    internal abstract fun bindAlquilerViewModel(alquilerViewModel: AlquilerViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


}