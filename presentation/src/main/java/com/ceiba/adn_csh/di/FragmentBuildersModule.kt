package com.ceiba.adn_csh.di

import com.ceiba.adn_csh.rental.RentalCreateFragment
import com.ceiba.adn_csh.rental.RentalDetailFragment
import com.ceiba.adn_csh.rental.RentalListFragment
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