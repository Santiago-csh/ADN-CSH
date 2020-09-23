package com.ceiba.adn_csh.dominio.di

import android.app.Application
import androidx.room.Room
import com.ceiba.adn_csh.dominio.repositorio.AlquilerRepositorio
import com.ceiba.adn_csh.dominio.servicios.alquiler.ServicioAlquiler
import com.ceiba.adn_csh.dominio.servicios.alquiler.ServicioAlquilerImpl
import com.ceiba.adn_csh.infraestructura.db.Database
import com.ceiba.adn_csh.infraestructura.db.dao.AlquilerDao
import com.ceiba.adn_csh.infraestructura.repositorioImpl.AlquilerRepositorioImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModulo::class])
class AppModulo {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): Database {
        return Room.databaseBuilder(app, Database::class.java, "adn").build()
    }

    @Singleton
    @Provides
    fun provideAlquilerDao(db: Database): AlquilerDao {
        return db.alquilerDao()
    }

    @Singleton
    @Provides
    fun provideServicioAlquiler(alquilerRepositorio: AlquilerRepositorio): ServicioAlquiler {
        return ServicioAlquilerImpl(alquilerRepositorio)
    }

    @Singleton
    @Provides
    fun provideAlquilerRepositorio(alquilerDao: AlquilerDao): AlquilerRepositorio {
        return AlquilerRepositorioImpl(alquilerDao)
    }

}