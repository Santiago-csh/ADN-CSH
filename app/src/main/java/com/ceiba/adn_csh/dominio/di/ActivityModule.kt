package com.ceiba.adn_csh.dominio.di

import com.ceiba.adn_csh.presentation.rental.DashboardActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeDashboardActivity(): DashboardActivity?

}