package com.ceiba.adn_csh.servicios

import android.util.Log
import com.ceiba.adn_csh.datos.ObjetoAlquiler
import com.ceiba.adn_csh.dominio.excepciones.ExcepcionNegocio
import com.ceiba.adn_csh.dominio.repositorio.AlquilerRepositorio
import com.ceiba.adn_csh.dominio.servicios.alquiler.ServicioAlquilerImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.mockito.*
import java.lang.Exception
import java.util.*

class ServicioAlquilerTest {

    @InjectMocks
    lateinit var servicioCrearAlquiler: ServicioAlquilerImpl

    @Mock
    lateinit var alquilerRepositorio: AlquilerRepositorio

    @Before
    fun initialization(){
        MockitoAnnotations.initMocks(this)
        Mockito.mock(AlquilerRepositorio::class.java)
    }

    @Rule @JvmField
    var exceptionRule: ExpectedException = ExpectedException.none()

    @Test
    fun crearAlquilerYaExistente(){
        val alquiler = ObjetoAlquiler().crearAlquilerAutomovilPlacaBuena()
        Mockito.`when`(alquilerRepositorio.vehiculoAlquilado(alquiler.vehiculo!!.placa!!)).thenReturn(true)
        Mockito.`when`(alquilerRepositorio.obtenerCantidadVehiculosAlquiladosPorTipo(alquiler.vehiculo!!.tipoVehiculo!!)).thenReturn(10)

        exceptionRule.expect(ExcepcionNegocio::class.java)
        exceptionRule.expectMessage("El vehiculo ya se encuentra alquilado.")

        servicioCrearAlquiler.crearAlquiler(alquiler)
    }

    @Test
    fun crearAlquilerValidacionEspacioParqueaderoAutomovil(){
        val alquiler = ObjetoAlquiler().crearAlquilerAutomovilPlacaBuena()
        Mockito.`when`(alquilerRepositorio.obtenerCantidadVehiculosAlquiladosPorTipo(alquiler.vehiculo!!.tipoVehiculo!!)).thenReturn(20)

        exceptionRule.expect(ExcepcionNegocio::class.java)
        exceptionRule.expectMessage("Lo sentimos, el parqueadero no tiene espacio para ${alquiler.vehiculo!!.tipoVehiculo!!.toLowerCase()}.")

        servicioCrearAlquiler.crearAlquiler(alquiler)
    }

    @Test
    fun crearAlquilerValidacionEspacioParqueaderoMotocicleta(){
        val alquiler = ObjetoAlquiler().crearAlquilerMotocicletaPlacaBuena()
        Mockito.`when`(alquilerRepositorio.obtenerCantidadVehiculosAlquiladosPorTipo(alquiler.vehiculo!!.tipoVehiculo!!)).thenReturn(10)

        exceptionRule.expect(ExcepcionNegocio::class.java)
        exceptionRule.expectMessage("Lo sentimos, el parqueadero no tiene espacio para ${alquiler.vehiculo!!.tipoVehiculo!!.toLowerCase()}.")

        servicioCrearAlquiler.crearAlquiler(alquiler)
    }

    @Test
    fun crearAlquilerMalPorPlacaAutomovil(){
        val alquiler = ObjetoAlquiler().crearAlquilerAutomovilPlacaMala()
        try {
            servicioCrearAlquiler.crearAlquiler(alquiler)
        }catch (e: ExcepcionNegocio){
            var dd = e.message
            Assert.assertEquals("La placa del vehiculo no es valida.", e.message)
        }
    }

    @Test
    fun crearAlquilerMalPorPlacaMotocicleta(){
        val alquiler = ObjetoAlquiler().crearAlquilerMotocicletaPlacaMala()
        exceptionRule.expect(ExcepcionNegocio::class.java)
        exceptionRule.expectMessage("La placa del vehiculo no es valida.")
        servicioCrearAlquiler.crearAlquiler(alquiler)
    }

    @Test
    fun crearAlquilerValidacionPrimeraLetraPlaca(){
        val alquiler = ObjetoAlquiler().crearAlquilerAutomovilValidacionPrimeraLetra()
        val diaActual = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        if((diaActual != Calendar.MONDAY && diaActual != Calendar.SUNDAY)){
            exceptionRule.expect(ExcepcionNegocio::class.java)
            exceptionRule.expectMessage("No esta autorizado tu ingreso.")
            servicioCrearAlquiler.crearAlquiler(alquiler)
            Mockito.verify(alquilerRepositorio, Mockito.times(0)).crearAlquiler(alquiler)
        }else{
            servicioCrearAlquiler.crearAlquiler(alquiler)
            Mockito.verify(alquilerRepositorio, Mockito.times(1)).crearAlquiler(alquiler)
        }
    }

    @Test
    fun crearAlquilerMotocicleta(){
        val alquiler = ObjetoAlquiler().crearAlquilerMotocicletaPlacaBuena()

        servicioCrearAlquiler.crearAlquiler(alquiler)

        Mockito.verify(alquilerRepositorio, Mockito.times(1)).crearAlquiler(alquiler)
    }

    @Test
    fun crearAlquilerDatosNull(){
        try {
            val alquiler = ObjetoAlquiler().crearAlquilerMotocicletaPlacaBuena()
            alquiler.vehiculo!!.placa = null
            servicioCrearAlquiler.crearAlquiler(alquiler)
        }catch (e: Exception){
            Log.e("ServicioCrearAlquiler", "", e)
        }
    }
}