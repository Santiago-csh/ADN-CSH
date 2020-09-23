package com.ceiba.adn_csh.dominio.di

import android.app.Application
import com.ceiba.adn_csh.presentacion.actividad.DashboardActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActividadesModulo {
    
    @ContributesAndroidInjector(modules = [FragmentBuildersModulo::class])
    abstract fun contributeDashboardActivity(): DashboardActivity?

}