package com.ceiba.domain.service.businesscomposite.calculatepricevehicle

import com.ceiba.domain.model.Rental

interface CalculatePriceComponent {
    fun execute(rental: Rental): Double
}