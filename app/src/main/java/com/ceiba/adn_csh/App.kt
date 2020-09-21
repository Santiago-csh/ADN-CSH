package com.ceiba.adn_csh

import android.app.Application
import com.ceiba.adn_csh.dominio.di.DaggerAppComponente
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App: Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(){
        super.onCreate()
        DaggerAppComponente.builder().application(this)!!.build().inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector;
    }
}