package com.ceiba.adn_csh.dominio.di

import com.ceiba.adn_csh.presentacion.fragmento.CrearAlquilerFragmento
import com.ceiba.adn_csh.presentacion.fragmento.ListaAlquileresFragmento
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModulo {

    @ContributesAndroidInjector
    abstract fun contributeListaAlquileresFragmento(): ListaAlquileresFragmento?

    @ContributesAndroidInjector
    abstract fun contributeCrearAlquilerFragmento(): CrearAlquilerFragmento?

}