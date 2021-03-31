package com.ceiba.adn_csh.feature.rental.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceiba.domain.model.Rental
import com.ceiba.domain.service.RentalService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RentalViewModel @Inject constructor(private var rentalService: RentalService): ViewModel() {

    var rentals =  MutableLiveData<List<Rental>>()
    var rentalDetail = MutableLiveData<Rental>()

    fun fetchActiveRentals() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                rentals.postValue(rentalService.getActiveRentals())
            }
        }
    }

    fun fetchRental(idRental: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                rentalDetail.postValue(rentalService.getRental(idRental))
            }
        }
    }

    fun getActiveRentals(): LiveData<List<Rental>> {
        return rentals
    }

    fun createRental(rental: Rental): LiveData<Long> {
        val idRental = MutableLiveData<Long>()
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                idRental.postValue(rentalService.createRental(rental))
            }
        }
        return idRental
    }

    fun updateRentalByPayment(): LiveData<Rental>{
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                rentalDetail.postValue(rentalService.updateRentalMakePayment(rentalDetail.value!!))
            }
        }
        return rentalDetail
    }

}