package com.ceiba.adn_csh.dominio.service.rental.business_exception_chain

import com.ceiba.adn_csh.dominio.model.Rental

interface CreateRentalChain{

    fun validate(rental: Rental): Boolean

}