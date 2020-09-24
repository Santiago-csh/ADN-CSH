package com.ceiba.adn_csh.dominio.service.rental.business_responsability_chain.create_rental_chain

import com.ceiba.adn_csh.dominio.model.Rental

interface CreateRentalChain{

    fun validate(rental: Rental): Boolean

}