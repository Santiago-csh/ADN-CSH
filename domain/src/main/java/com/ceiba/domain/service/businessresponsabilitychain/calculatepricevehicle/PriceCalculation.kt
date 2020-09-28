package com.ceiba.domain.service.businessresponsabilitychain.calculatepricevehicle

import com.ceiba.domain.model.Rental

interface PriceCalculation {
    fun calculatePrice(rental: Rental): Double
}