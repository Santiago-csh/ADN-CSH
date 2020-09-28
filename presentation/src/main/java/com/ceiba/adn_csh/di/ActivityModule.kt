package com.ceiba.adn_csh.di

import com.ceiba.adn_csh.rental.DashboardActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeDashboardActivity(): DashboardActivity?

}