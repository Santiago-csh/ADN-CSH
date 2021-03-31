package com.ceiba.domain.service.businesscomposite.validationscreaterental

import com.ceiba.domain.model.Rental

interface CreateRentalComponent{
    fun execute(rental: Rental)
}