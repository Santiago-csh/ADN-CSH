package com.ceiba.adn_csh.dominio.di

import com.ceiba.adn_csh.presentation.rental.RentalCreateFragment
import com.ceiba.adn_csh.presentation.rental.RentalDetailFragment
import com.ceiba.adn_csh.presentation.rental.RentalListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeRentalListFragment(): RentalListFragment?

    @ContributesAndroidInjector
    abstract fun contributeRentalCreateFragment(): RentalCreateFragment?

    @ContributesAndroidInjector
    abstract fun contributeRentalDetailFragment(): RentalDetailFragment?

}