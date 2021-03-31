package com.ceiba.domain.service

import com.ceiba.domain.build.RentalBuilder
import com.ceiba.domain.build.VehicleBuilder
import com.ceiba.domain.exception.ParkingSpaceException
import com.ceiba.domain.repository.RentalRepository
import com.ceiba.domain.exception.RentedVehicleException
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.mockito.*
import java.lang.StringBuilder
import java.util.*

const val PARKING_LOT_DOES_NOT_HAVE_SPACE_FOR_VEHICLE_MESSAGE  = "Lo sentimos, el parqueadero no tiene espacio para "

class RentalServiceTest {

    companion object {
        const val CAR = "AUTOMOVIL"
        const val MOTORCYCLE = "MOTOCICLETA"
    }

    @InjectMocks
    lateinit var servicioCrearAlquiler: RentalServiceImpl

    @Mock
    lateinit var rentalRepository: RentalRepository

    @Before
    fun initialization(){
        MockitoAnnotations.initMocks(this)
        Mockito.mock(RentalRepository::class.java)

    }

    @Rule @JvmField
    var exceptionRule: ExpectedException = ExpectedException.none()

    @Test
    fun given_Vehicle_Already_Exists_When_CreateRental_Is_Called_Then_It_Must_Return_RentedVehicleException(){
        // Arrange
        val rental = RentalBuilder()
            .withVehicle(
                VehicleBuilder()
                    .withPlate("EES999")
                    .withVehicleType(CAR)
                    .build()
            ).build()
        Mockito.`when`(rentalRepository.rentedVehicle(rental.vehicle.plate)).thenReturn(true)
        Mockito.`when`(rentalRepository.getQuantityOfRentedVehiclesByType(rental.vehicle.vehicleType)).thenReturn(10)

        // Assert
        exceptionRule.expect(RentedVehicleException::class.java)
        exceptionRule.expectMessage("El vehiculo ya se encuentra alquilado.")

        // Act
        servicioCrearAlquiler.createRental(rental)
    }

    @Test
    fun given_There_Is_Not_Space_For_Cars_When_CreateRental_Is_Called_Then_It_Must_Return_ParkingSpaceException(){
        // Arrange
        val rental = RentalBuilder()
            .withVehicle(
                VehicleBuilder()
                    .withPlate("EHS939")
                    .withVehicleType(CAR)
                    .build()
            ).build()
        Mockito.`when`(rentalRepository.getQuantityOfRentedVehiclesByType(rental.vehicle.vehicleType)).thenReturn(20)

        // Assert
        exceptionRule.expect(ParkingSpaceException::class.java)
        exceptionRule.expectMessage(
            StringBuilder().append(PARKING_LOT_DOES_NOT_HAVE_SPACE_FOR_VEHICLE_MESSAGE).append("${rental.vehicle.vehicleType.toLowerCase()}.").toString()
        )

        // Act
        servicioCrearAlquiler.createRental(rental)
    }

    @Test
    fun given_There_Is_Not_Space_For_Motorcycles_When_CreateRental_Is_Called_Then_It_Must_Return_ParkingSpaceException(){
        // Arrange
        val rental = RentalBuilder()
            .withVehicle(
                VehicleBuilder()
                    .withPlate("DCF15B")
                    .withVehicleType(MOTORCYCLE)
                    .build()
            ).build()
        Mockito.`when`(rentalRepository.getQuantityOfRentedVehiclesByType(rental.vehicle.vehicleType)).thenReturn(10)

        // Assert
        exceptionRule.expect(ParkingSpaceException::class.java)
        exceptionRule.expectMessage(
            StringBuilder().append(PARKING_LOT_DOES_NOT_HAVE_SPACE_FOR_VEHICLE_MESSAGE).append("${rental.vehicle.vehicleType.toLowerCase()}.").toString()
        )

        // Act
        servicioCrearAlquiler.createRental(rental)
    }

    @Test
    fun given_A_Motorcycle_Rent_When_CreateRental_Is_Called_Then_The_Rental_Id_Must_Be_Non_Zero(){
        // Arrange
        val rental = RentalBuilder()
            .withVehicle(
                VehicleBuilder()
                    .withPlate("DLF65B")
                    .withVehicleType(MOTORCYCLE)
                    .build()
            ).build()
        Mockito.`when`(rentalRepository.createRental(rental)).thenReturn(1)

        // Act
        val idRental = servicioCrearAlquiler.createRental(rental)

        // Assert
        Assert.assertTrue(idRental != 0L)
    }

    @Test
    fun given_The_Price_Of_A_Car_Rental_When_CalculateVehiclePrice_Is_Called_Then_It_Must_Return_That_Price(){
        // Arrange
        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR] = calendar[Calendar.HOUR] + 32
        val rental = RentalBuilder()
            .withVehicle(
                VehicleBuilder()
                    .withPlate("ILG853")
                    .withVehicleType(CAR)
                    .build()
            ).withDepartureDate(calendar.time)
            .build()

        // Act
        val response = servicioCrearAlquiler.calculateVehiclePrice(rental)

        // Assert
        Assert.assertEquals(16000, response.toLong())
    }

    @Test
    fun given_The_Price_Of_A_Motorcycle_Rental_With_A_Cylinder_Capacity_Greater_Than_Five_Hundred_When_CalculateVehiclePrice_Is_Called_Then_It_Must_Return_That_Price(){
        // Arrange
        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR] = calendar[Calendar.HOUR] + 127
        val rental = RentalBuilder()
            .withVehicle(
                VehicleBuilder()
                    .withPlate("IYG85G")
                    .withCylinderCapacity(550)
                    .withVehicleType(MOTORCYCLE)
                    .build()
            ).withDepartureDate(calendar.time)
            .build()

        // Act
        val response = servicioCrearAlquiler.calculateVehiclePrice(rental)

        // Assert
        Assert.assertEquals(25500, response.toLong())
    }
}