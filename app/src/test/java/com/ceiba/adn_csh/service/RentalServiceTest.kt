package com.ceiba.adn_csh.service

import android.util.Log
import androidx.lifecycle.LiveData
import com.ceiba.adn_csh.build.RentalBuilder
import com.ceiba.adn_csh.dominio.exception.BusinessException
import com.ceiba.adn_csh.dominio.repository.RentalRepository
import com.ceiba.adn_csh.dominio.service.rental.RentalServiceImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.mockito.*
import java.lang.Exception
import java.util.*

class RentalServiceTest {

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
    fun crearAlquilerYaExistente(){
        val alquiler = RentalBuilder().createRentCarPlateGood()
        Mockito.`when`(rentalRepository.rentedVehicle(alquiler.vehicle!!.plate!!)).thenReturn(true)
        Mockito.`when`(rentalRepository.getQuantityOfRentedVehiclesByType(alquiler.vehicle!!.vehicleType!!)).thenReturn(10)

        exceptionRule.expect(BusinessException::class.java)
        exceptionRule.expectMessage("El vehiculo ya se encuentra alquilado.")

        servicioCrearAlquiler.createRental(alquiler)
    }

    @Test
    fun crearAlquilerValidacionEspacioParqueaderoAutomovil(){
        val alquiler = RentalBuilder().createRentCarPlateGood()
        Mockito.`when`(rentalRepository.getQuantityOfRentedVehiclesByType(alquiler.vehicle!!.vehicleType!!)).thenReturn(20)

        exceptionRule.expect(BusinessException::class.java)
        exceptionRule.expectMessage("Lo sentimos, el parqueadero no tiene espacio para ${alquiler.vehicle!!.vehicleType!!.toLowerCase()}.")

        servicioCrearAlquiler.createRental(alquiler)
    }

    @Test
    fun crearAlquilerValidacionEspacioParqueaderoMotocicleta(){
        val alquiler = RentalBuilder().createRentMotorcyclePlateGood()
        Mockito.`when`(rentalRepository.getQuantityOfRentedVehiclesByType(alquiler.vehicle!!.vehicleType!!)).thenReturn(10)

        exceptionRule.expect(BusinessException::class.java)
        exceptionRule.expectMessage("Lo sentimos, el parqueadero no tiene espacio para ${alquiler.vehicle!!.vehicleType!!.toLowerCase()}.")

        servicioCrearAlquiler.createRental(alquiler)
    }

    @Test
    fun crearAlquilerMalPorPlacaAutomovil(){
        val alquiler = RentalBuilder().createRentCarPlateBad()
        try {
            servicioCrearAlquiler.createRental(alquiler)
        }catch (e: BusinessException){
            var dd = e.message
            Assert.assertEquals("La placa del vehiculo no es valida.", e.message)
        }
    }

    @Test
    fun crearAlquilerMalPorPlacaMotocicleta(){
        val alquiler = RentalBuilder().createRentMotorcyclePlateBad()
        exceptionRule.expect(BusinessException::class.java)
        exceptionRule.expectMessage("La placa del vehiculo no es valida.")
        servicioCrearAlquiler.createRental(alquiler)
    }

    @Test
    fun crearAlquilerValidacionPrimeraLetraPlaca(){
        val alquiler = RentalBuilder().createRentCarValidationFirstLetter()
        val diaActual = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        if((diaActual != Calendar.MONDAY && diaActual != Calendar.SUNDAY)){
            exceptionRule.expect(BusinessException::class.java)
            exceptionRule.expectMessage("No esta autorizado tu ingreso.")
            servicioCrearAlquiler.createRental(alquiler)
            Mockito.verify(rentalRepository, Mockito.times(0)).createRental(alquiler)
        }else{
            servicioCrearAlquiler.createRental(alquiler)
            Mockito.verify(rentalRepository, Mockito.times(1)).createRental(alquiler)
        }
    }

    @Test
    fun crearAlquilerMotocicleta(){
        val alquiler = RentalBuilder().createRentMotorcyclePlateGood()

        servicioCrearAlquiler.createRental(alquiler)

        Mockito.verify(rentalRepository, Mockito.times(1)).createRental(alquiler)
    }

    @Test
    fun crearAlquilerDatosNull(){
        try {
            val alquiler = RentalBuilder().createRentMotorcyclePlateGood()
            alquiler.vehicle!!.plate = null
            servicioCrearAlquiler.createRental(alquiler)
        }catch (e: Exception){
            Log.e("ServicioCrearAlquiler", "", e)
        }
    }

    @Test
    fun listRentals(){
        val rentalList = RentalBuilder().createRentalList()
        Mockito.`when`(rentalRepository.getActiveRentals()).thenReturn(rentalList)

        val result = servicioCrearAlquiler.getActiveRentals()

        Assert.assertEquals(rentalList, result)
    }

    @Test
    fun getCarPriceWithHoursAndMinutes(){
        val rental = RentalBuilder().createRentCarWithHoursAndMinutes()
        val timeInParking = (rental.departureDate!!.time - rental.arrivalDate!!.time)
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
        val timeInParking = (rental.departureDate!!.time - rental.arrivalDate!!.time)
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
        val timeInParking = (rental.departureDate!!.time - rental.arrivalDate!!.time)
        val minutesInParking = timeInParking / 60000
        var hoursInParking = minutesInParking / 60
        if((minutesInParking - (hoursInParking*60)) > 0){
            hoursInParking += 1
        }
        val expectedPrice = (hoursInParking * 500) + 2000

        val response = servicioCrearAlquiler.calculateVehiclePrice(rental)

        Assert.assertEquals(expectedPrice, response.toLong())
    }

    @Test
    fun getMotorcyclePriceWithCylinderCapacityOfLessOrEqualsThanFiveHundredWithHoursAndWithoutDays(){
        val rental = RentalBuilder().createRentMotorcycleCylinderCapacityLessOrEqualsThanFiveHundredWithHoursAndWithoutDays()
        val timeInParking = (rental.departureDate!!.time - rental.arrivalDate!!.time)
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