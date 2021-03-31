package com.ceiba.adn_csh.di

import com.ceiba.adn_csh.feature.rental.fragments.RentalCreateFragment
import com.ceiba.adn_csh.feature.rental.fragments.RentalDetailFragment
import com.ceiba.adn_csh.feature.rental.fragments.RentalListFragment
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