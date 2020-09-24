package com.ceiba.adn_csh.dominio.di

import android.app.Application
import androidx.room.Room
import com.ceiba.adn_csh.dominio.repository.RentalRepository
import com.ceiba.adn_csh.dominio.service.rental.RentalService
import com.ceiba.adn_csh.dominio.service.rental.RentalServiceImpl
import com.ceiba.adn_csh.infraestructure.db.Database
import com.ceiba.adn_csh.infraestructure.db.dao.RentalDao
import com.ceiba.adn_csh.infraestructure.repositoryImpl.RentalRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): Database {
        return Room.databaseBuilder(app, Database::class.java, "adn").build()
    }

    @Singleton
    @Provides
    fun provideAlquilerDao(db: Database): RentalDao {
        return db.rentalDao()
    }

    @Singleton
    @Provides
    fun provideRentalService(rentalRepository: RentalRepository): RentalService {
        return RentalServiceImpl(rentalRepository)
    }

    @Singleton
    @Provides
    fun provideRentalRepository(rentalDao: RentalDao): RentalRepository {
        return RentalRepositoryImpl(rentalDao)
    }

}