package com.ceiba.domain.model

import com.ceiba.domain.build.RentalBuilder
import com.ceiba.domain.build.VehicleBuilder
import com.ceiba.domain.exception.FirstPlateLetterException
import com.ceiba.domain.exception.PlateFormatException
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import java.util.*

private const val LICENSE_PLATE_OF_THE_VEHICLE_IS_NOT_VALID = "La placa ingresada no es valida."

class VehicleModelTest {

    companion object {
        const val CAR = "AUTOMOVIL"
        const val MOTORCYCLE = "MOTOCICLETA"
    }

    @Rule
    @JvmField
    var exceptionRule: ExpectedException = ExpectedException.none()

    @Test
    fun given_The_Car_Plate_Is_Wrong_When_CreateRental_Is_Called_Then_It_Must_Return_PlateFormatException(){
        try {
            // Act
            RentalBuilder()
                .withVehicle(VehicleBuilder()
                    .withPlate("I5S8T")
                    .withVehicleType(CAR)
                    .build()
                ).build()
        }catch (e: PlateFormatException){
            // Assert
            Assert.assertEquals(LICENSE_PLATE_OF_THE_VEHICLE_IS_NOT_VALID, e.message)
        }
    }

    @Test
    fun given_The_Motorcycle_Plate_Is_Wrong_When_CreateRental_Is_Called_Then_It_Must_Return_PlateFormatException(){
        // Assert
        exceptionRule.expect(PlateFormatException::class.java)
        exceptionRule.expectMessage(LICENSE_PLATE_OF_THE_VEHICLE_IS_NOT_VALID)

        // Act
        RentalBuilder()
            .withVehicle(VehicleBuilder()
                .withPlate("IYG855")
                .withVehicleType(MOTORCYCLE)
                .build())
            .build()
    }

    @Test
    fun given_The_Plate_Starts_With_A_And_It_Is_Not_Sunday_Or_Monday_When_CreateRental_Is_Called_Then_It_Must_Return_FirstPlateLetterException(){
        // Arrange
        val diaActual = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        if(diaActual != Calendar.MONDAY && diaActual != Calendar.SUNDAY){
            // Assert
            exceptionRule.expect(FirstPlateLetterException::class.java)
            exceptionRule.expectMessage("No esta autorizado tu ingreso.")
        }
        // Act
        RentalBuilder()
            .withVehicle(VehicleBuilder()
                .withPlate("ACJ192")
                .withVehicleType(CAR)
                .build())
            .build()
    }

}