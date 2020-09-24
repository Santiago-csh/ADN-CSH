package com.ceiba.adn_csh.dominio.service.rental.business_exception_chain

import com.ceiba.adn_csh.dominio.exception.BusinessException
import com.ceiba.adn_csh.dominio.model.Rental
import java.util.*

class FirstPlateLetter: CreateRentalChain {

    override fun validate(rental: Rental): Boolean {
        if (rental.vehicle!!.plate!![0].toUpperCase() == 'A') {
            val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
            if (currentDay != Calendar.MONDAY && currentDay != Calendar.SUNDAY) {
                throw BusinessException("No esta autorizado tu ingreso.")
            }
        }
        return true
    }

}