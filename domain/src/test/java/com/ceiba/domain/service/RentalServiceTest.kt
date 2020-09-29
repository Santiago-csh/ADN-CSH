package com.ceiba.domain.service

import com.ceiba.domain.build.RentalBuilder
import com.ceiba.adn_csh.domain.repository.RentalRepository
import com.ceiba.domain.exception.BusinessException
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.mockito.*
import java.lang.StringBuilder
import java.util.*

class RentalServiceTest {

    @InjectMocks
    lateinit var servicioCrearAlquiler: RentalServiceImpl

    @Mock
    lateinit var rentalRepository: RentalRepository

    private lateinit var messageVehicleIsAlreadyRented: String
    private lateinit var messageParkingLotDoesNotHaveSpaceForVehcile: String
    private lateinit var messageLicensePlateOfTheVehicleIsNotValid: String
    private lateinit var messageYourEntryIsNotAuthorized: String
    private lateinit var messageCreateRentalTest: String


    @Before
    fun initialization(){
        MockitoAnnotations.initMocks(this)
        Mockito.mock(RentalRepository::class.java)

        messageVehicleIsAlreadyRented = "El vehiculo ya se encuentra alquilado."
        messageParkingLotDoesNotHaveSpaceForVehcile = "Lo sentimos, el parqueadero no tiene espacio para "
        messageLicensePlateOfTheVehicleIsNotValid = "La placa ingresada no es valida."
        messageYourEntryIsNotAuthorized = "No esta autorizado tu ingreso."
        messageCreateRentalTest = "createRentalTest"
    }

    @Rule @JvmField
    var exceptionRule: ExpectedException = ExpectedException.none()

    @Test
    fun crearAlquilerYaExistente(){
        val alquiler = RentalBuilder().createRentCarPlateGood()
        Mockito.`when`(rentalRepository.rentedVehicle(alquiler.vehicle.plate)).thenReturn(true)
        Mockito.`when`(rentalRepository.getQuantityOfRentedVehiclesByType(alquiler.vehicle.vehicleType)).thenReturn(10)

        exceptionRule.expect(BusinessException::class.java)
        exceptionRule.expectMessage(messageVehicleIsAlreadyRented)

        servicioCrearAlquiler.createRental(alquiler)
    }

    @Test
    fun crearAlquilerValidacionEspacioParqueaderoAutomovil(){
        val alquiler = RentalBuilder().createRentCarPlateGood()
        Mockito.`when`(rentalRepository.getQuantityOfRentedVehiclesByType(alquiler.vehicle.vehicleType)).thenReturn(20)

        exceptionRule.expect(BusinessException::class.java)
        exceptionRule.expectMessage(
            StringBuilder().append(messageParkingLotDoesNotHaveSpaceForVehcile).append("${alquiler.vehicle.vehicleType.toLowerCase()}.").toString()
        )

        servicioCrearAlquiler.createRental(alquiler)
    }

    @Test
    fun crearAlquilerValidacionEspacioParqueaderoMotocicleta(){
        val alquiler = RentalBuilder().createRentMotorcyclePlateGood()
        Mockito.`when`(rentalRepository.getQuantityOfRentedVehiclesByType(alquiler.vehicle.vehicleType)).thenReturn(10)

        exceptionRule.expect(BusinessException::class.java)
        exceptionRule.expectMessage(
            StringBuilder().append(messageParkingLotDoesNotHaveSpaceForVehcile).append("${alquiler.vehicle.vehicleType.toLowerCase()}.").toString()
        )

        servicioCrearAlquiler.createRental(alquiler)
    }

    @Test
    fun crearAlquilerMalPorPlacaAutomovil(){
        try {
            RentalBuilder().createRentCarPlateBad()
        }catch (e: BusinessException){
            Assert.assertEquals(messageLicensePlateOfTheVehicleIsNotValid, e.message)
        }
    }

    @Test
    fun crearAlquilerMalPorPlacaMotocicleta(){
        exceptionRule.expect(BusinessException::class.java)
        exceptionRule.expectMessage(messageLicensePlateOfTheVehicleIsNotValid)
        RentalBuilder().createRentMotorcyclePlateBad()
    }

    @Test
    fun crearAlquilerValidacionPrimeraLetraPlaca(){
        val diaActual = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        if(diaActual != Calendar.MONDAY && diaActual != Calendar.SUNDAY){
            exceptionRule.expect(BusinessException::class.java)
            exceptionRule.expectMessage(messageYourEntryIsNotAuthorized)
        }
        RentalBuilder().createRentCarValidationFirstLetter()
    }

    @Test
    fun crearAlquilerMotocicleta(){
        val alquiler = RentalBuilder().createRentMotorcyclePlateGood()

        servicioCrearAlquiler.createRental(alquiler)

        Mockito.verify(rentalRepository, Mockito.times(1)).createRental(alquiler)
    }

    @Test
    fun getCarPriceWithHoursAndMinutes(){
        val rental = RentalBuilder().createRentCarWithHoursAndMinutes()
        val timeInParking = (rental.departureDate.time - rental.arrivalDate.time)
        val minutesInParking = timeInParking / 60000
        var hoursInParking = minutesInParking / 60
        if((minutesInParking - (hoursInParking*60)) > 0){
            hoursInParking += 1
        }
        val expectedPrice = (hoursInParking * 1000)

        val response = servicioCrearAlquiler.calculateVehiclePrice(rental)

        Assert.assertEquals(expectedPrice, response.toLong())
    }

    @Test
    fun getCarPriceWithHoursAndWithoutDays(){
        val rental = RentalBuilder().createRentCarWithHoursAndWithoutDays()
        val timeInParking = (rental.departureDate.time - rental.arrivalDate.time)
        val hoursInParking = timeInParking / 3600000
        val expectedPrice = (hoursInParking * 1000)

        val response = servicioCrearAlquiler.calculateVehiclePrice(rental)

        Assert.assertEquals(expectedPrice, response.toLong())
    }

    @Test
    fun getCarPriceWithDaysAndWithoutHours(){
        val rental = RentalBuilder().createRentCarWithDaysAndWithoutHours()

        val response = servicioCrearAlquiler.calculateVehiclePrice(rental)

        Assert.assertEquals(16000, response.toLong())
    }

    @Test
    fun getCarPriceWithDaysAndHours(){
        val rental = RentalBuilder().createRentCarWithDaysAndHours()

        val response = servicioCrearAlquiler.calculateVehiclePrice(rental)

        Assert.assertEquals(15000, response.toLong())
    }

    @Test
    fun getMotorcyclePriceCylinderCapacityOfGreaterThanFiveHundredWithHoursAndMinutes(){
        val rental = RentalBuilder().createRentMotorcycleCylinderCapacityOfGreaterThanFiveHundredWithHoursAndMinutes()
        val timeInParking = (rental.departureDate.time - rental.arrivalDate.time)
        val minutesInParking = timeInParking / 60000
        var hoursInParking = minutesInParking / 60
        if((minutesInParking - (hoursInParking*60)) > 0){
            hoursInParking = hoursInParking + 1
        }
        val expectedPrice = (hoursInParking * 500) + 2000

        val response = servicioCrearAlquiler.calculateVehiclePrice(rental)

        Assert.assertEquals(expectedPrice, response.toLong())
    }

    @Test
    fun getMotorcyclePriceWithCylinderCapacityOfLessOrEqualsThanFiveHundredWithHoursAndWithoutDays(){
        val rental = RentalBuilder().createRentMotorcycleCylinderCapacityLessOrEqualsThanFiveHundredWithHoursAndWithoutDays()
        val timeInParking = (rental.departureDate.time - rental.arrivalDate.time)
        val hoursInParking = timeInParking / 3600000
        val expectedPrice = (hoursInParking * 500)

        val response = servicioCrearAlquiler.calculateVehiclePrice(rental)

        Assert.assertEquals(expectedPrice, response.toLong())
    }

    @Test
    fun getMotorcyclePriceWithCylinderCapacityOfGreaterThanFiveHundredWithDaysAndWithoutHours(){
        val rental = RentalBuilder().createRentMotorcycleCylinderCapacityGreaterThanFiveHundredWithDaysAndWithoutHours()

        val response = servicioCrearAlquiler.calculateVehiclePrice(rental)

        Assert.assertEquals(18000, response.toLong())
    }

    @Test
    fun getMotorcyclePriceWithCylinderCapacityOfGreaterThanFiveHundredWithHoursAndDays(){
        val rental = RentalBuilder().createRentMotorcycleCylinderCapacityGreaterThanFiveHundredWithDaysAndHours()

        val response = servicioCrearAlquiler.calculateVehiclePrice(rental)

        Assert.assertEquals(25500, response.toLong())
    }

}