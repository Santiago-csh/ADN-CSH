package com.ceiba.domain.service.businessresponsabilitychain.validationscreaterental

import com.ceiba.domain.model.Rental

interface ValidationsCreateRental{
    fun validation(rental: Rental): Boolean
}