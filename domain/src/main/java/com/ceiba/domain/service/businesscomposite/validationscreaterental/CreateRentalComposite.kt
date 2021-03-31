package com.ceiba.domain.service.businesscomposite.validationscreaterental

import com.ceiba.domain.model.Rental
import java.util.ArrayList

class CreateRentalComposite: CreateRentalComponent {

    private val children = ArrayList<CreateRentalComponent>()

    fun add(component: CreateRentalComponent){
        children.add(component)
    }

    override fun execute(rental: Rental) {
        for (component in children){
            component.execute(rental)
        }
    }
}