package com.ceiba.adn_csh.dominio.di

import android.app.Application
import androidx.room.Room
import com.ceiba.adn_csh.infraestructura.Database
import com.ceiba.adn_csh.infraestructura.dao.AlquilerDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

//@Module(includes = [ViewModelModule::class])
@Module
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

}