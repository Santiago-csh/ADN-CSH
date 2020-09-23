package com.ceiba.adn_csh.dominio.di

import android.app.Application
import com.ceiba.adn_csh.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModulo::class,
    ActividadesModulo::class
])
interface AppComponente {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder?
        fun build(): AppComponente
    }
}
