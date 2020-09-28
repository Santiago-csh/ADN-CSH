package com.ceiba.domain.model

import com.ceiba.domain.exception.EmptyValuesException
import com.ceiba.domain.exception.FirstPlateLetterException
import java.util.*
import java.util.regex.Pattern

open class Vehicle(plate: String, cylinderCapacity: Int, var vehicleType: String) {

    var plate: String = ""
        set(newPlate) {
            field = validatePlate(newPlate)
        }

    var cylinderCapacity: Int

    init {
        this.plate = plate
        this.cylinderCapacity = cylinderCapacity
    }

    private fun validatePlate(newPlate: String): String {
        validatePlateFormat(newPlate)
        validateFirstPlateLetter(newPlate)
        return newPlate
    }

    private fun validatePlateFormat(newPlate: String){
        val vehiclePlateFormat: Map<String, String> = mapOf("AUTOMOVIL" to "^[a-zA-Z]{3}[0-9]{3}\$", "MOTOCICLETA" to "^[a-zA-Z]{3}[0-9]{2}[a-zA-Z]\$")
        for((key, value) in vehiclePlateFormat){
            if(key == vehicleType.toUpperCase(Locale.ROOT)){
                val pattern = Pattern.compile(value)
                if(!pattern.matcher(newPlate).matches()){
                    throw EmptyValuesException("La placa ingresada no es valida.")
                }
            }
        }
    }

    private fun validateFirstPlateLetter(newPlate: String){
        if (newPlate[0].toUpperCase() == 'A') {
            val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
            if (currentDay != Calendar.MONDAY && currentDay != Calendar.SUNDAY) {
                throw FirstPlateLetterException("No esta autorizado tu ingreso.")
            }
        }
    }

}