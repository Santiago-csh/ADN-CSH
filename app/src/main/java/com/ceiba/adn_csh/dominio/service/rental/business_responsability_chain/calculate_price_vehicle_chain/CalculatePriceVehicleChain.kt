package com.ceiba.adn_csh.dominio.service.rental.business_responsability_chain.calculate_price_vehicle_chain

import com.ceiba.adn_csh.dominio.model.Rental

interface CalculatePriceVehicleChain {
    fun addValue(rental: Rental): Double
}