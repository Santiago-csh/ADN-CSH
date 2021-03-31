package com.ceiba.domain.service.businesscomposite.calculatepricevehicle

import com.ceiba.domain.model.Rental

class CalculatePriceComposite : CalculatePriceComponent {

    private val children = ArrayList<CalculatePriceComponent>()

    fun add(component: CalculatePriceComponent){
        children.add(component)
    }

    override fun execute(rental: Rental): Double {
        var price = 0.0
        for(component in children){
            price += component.execute(rental)
        }
        return price
    }

}