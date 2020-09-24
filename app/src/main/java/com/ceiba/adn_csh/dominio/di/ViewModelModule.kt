package com.ceiba.adn_csh.dominio.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ceiba.adn_csh.infraestructure.view_model.RentalViewModel
import com.ceiba.adn_csh.infraestructure.view_model.factoria.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RentalViewModel::class)
    internal abstract fun bindRentalViewModel(rentalViewModel: RentalViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


}