package com.ceiba.adn_csh.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ceiba.adn_csh.feature.rental.viewmodel.RentalViewModel
import com.ceiba.adn_csh.viewmodel.factory.ViewModelFactory
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