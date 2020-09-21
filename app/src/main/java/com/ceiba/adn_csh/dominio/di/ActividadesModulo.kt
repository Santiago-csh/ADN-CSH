package com.ceiba.adn_csh.dominio.di

import com.ceiba.adn_csh.presentacion.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActividadesModulo {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

}