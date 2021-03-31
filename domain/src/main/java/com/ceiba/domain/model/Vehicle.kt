package com.ceiba.domain.model

import com.ceiba.domain.exception.PlateFormatException
import com.ceiba.domain.exception.FirstPlateLetterException
import java.util.*
import java.util.regex.Pattern

private const val CAR = "AUTOMOVIL"
private const val MOTORCYCLE = "MOTOCICLETA"
private const val REGULAR_EXPRESSION_CAR = "^[a-zA-Z]{3}[0-9]{3}\$"
private const val REGULAR_EXPRESSION_MOTORCYCLE = "^[a-zA-Z]{3}[0-9]{2}[a-zA-Z]\$"
private val VEHICLE_PLATE_FORMAT: Map<String, String> = mapOf(CAR to REGULAR_EXPRESSION_CAR, MOTORCYCLE to REGULAR_EXPRESSION_MOTORCYCLE)

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
        for((key, value) in VEHICLE_PLATE_FORMAT){
            if(key == vehicleType.toUpperCase(Locale.ROOT)){
                val pattern = Pattern.compile(value)
                if(!pattern.matcher(newPlate).matches()){
                    throw PlateFormatException()
                }
            }
        }
    }

    private fun validateFirstPlateLetter(newPlate: String){
        if (newPlate[0].toUpperCase() == 'A') {
            val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
            if (currentDay != Calendar.MONDAY && currentDay != Calendar.SUNDAY) {
                throw FirstPlateLetterException()
            }
        }
    }

}