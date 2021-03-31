package com.ceiba.adn_csh.di

import android.app.Application
import androidx.room.Room
import com.ceiba.domain.repository.RentalRepository
import com.ceiba.domain.service.RentalService
import com.ceiba.domain.service.RentalServiceImpl
import com.ceiba.infraestructure.db.Database
import com.ceiba.infraestructure.db.dao.RentalDao
import com.ceiba.infraestructure.repository.RentalRepositoryRoom
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): Database {
        return Room.databaseBuilder(app, Database::class.java, "adb_db").build()
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
        return RentalRepositoryRoom(rentalDao)
    }

}