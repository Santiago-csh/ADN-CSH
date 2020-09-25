package com.ceiba.adn_csh.view

import android.view.View
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.ceiba.adn_csh.ObjectUtils
import com.ceiba.adn_csh.R
import com.ceiba.adn_csh.presentation.rental.DashboardActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class RentalTest {

    private lateinit var objectUtils: ObjectUtils

    @Rule
    @JvmField
    val activityScenarioRule: ActivityScenarioRule<DashboardActivity> = ActivityScenarioRule(DashboardActivity::class.java)
    private lateinit var decorView: View

    @Before
    fun initialization(){
        objectUtils = ObjectUtils()
        activityScenarioRule.scenario.onActivity { activity ->
            decorView = activity.window.decorView
        }
    }

    @Throws(InterruptedException::class)
    @Test
    fun verifyThatTheRentalCreationFieldsAreNotEmpty(){
        ObjectUtils.openNavHostFragment()
        ObjectUtils.sleep(1)

        ObjectUtils.click(R.id.navigation_create_rental)
        ObjectUtils.sleep(1)
        ObjectUtils.click(R.id.btnAddRental)
        ObjectUtils.verifyDisplayToastAllways("Debes ingresar la placa.", decorView)
        ObjectUtils.sleep(1)
        ObjectUtils.fillIdEditText(R.id.etPlateVehicle, "XJC905")
        ObjectUtils.click(R.id.btnAddRental)
        ObjectUtils.sleep(1)
        ObjectUtils.verifyDisplayToastAllways("Debes ingresar el cilindraje.", decorView)
        ObjectUtils.sleep(1)
    }

    @Throws(InterruptedException::class)
    @Test
    fun createRentals(){
        ObjectUtils.openNavHostFragment()
        ObjectUtils.sleep(1)
        ObjectUtils.createCar("CGS763", 100)
        ObjectUtils.verifyDisplayToastCondition("Registro exitoso.", "El vehiculo ya se encuentra alquilado.", decorView)
        ObjectUtils.click(R.id.navigation_list_rental)
        ObjectUtils.sleep(1)

        ObjectUtils.createMotorcycle("lbc81s", 600)
        ObjectUtils.verifyDisplayToastCondition("Registro exitoso.", "El vehiculo ya se encuentra alquilado.", decorView)

        ObjectUtils.click(R.id.navigation_list_rental)
        ObjectUtils.sleep(1)
    }

    @Throws(InterruptedException::class)
    @Test
    fun createRentalExists(){
        ObjectUtils.openNavHostFragment()
        ObjectUtils.createCar("SMT609", 800)
        ObjectUtils.verifyDisplayToastCondition("Registro exitoso.", "El vehiculo ya se encuentra alquilado.", decorView)

        ObjectUtils.click(R.id.navigation_list_rental)
        ObjectUtils.sleep(1)
        ObjectUtils.createCar("SMT609", 400)
        ObjectUtils.verifyDisplayToastAllways("El vehiculo ya se encuentra alquilado.", decorView)
        ObjectUtils.sleep(2)
    }

    @Throws(InterruptedException::class)
    @Test
    fun createRentalWithBadPlate(){
        ObjectUtils.openNavHostFragment()
        ObjectUtils.sleep(1)
        ObjectUtils.click(R.id.navigation_create_rental)

        ObjectUtils.fillIdEditText(R.id.etPlateVehicle, "ZSU54L")
        ObjectUtils.sleep(1)
        ObjectUtils.fillIdEditText(R.id.etCylinderCapacityVehicle, "100")
        ObjectUtils.click(R.id.btnAddRental)
        ObjectUtils.verifyDisplayToastAllways("La placa del vehiculo no es valida.", decorView)
        ObjectUtils.sleep(2)

        ObjectUtils.fillIdEditText(R.id.etPlateVehicle, "ZSU547")
        ObjectUtils.sleep(1)
        ObjectUtils.fillIdEditText(R.id.etCylinderCapacityVehicle, "300")
        ObjectUtils.sleep(1)
        ObjectUtils.click(R.id.spinnerVehicleType)
        ObjectUtils.click("MOTOCICLETA")
        ObjectUtils.click(R.id.btnAddRental)
        ObjectUtils.verifyDisplayToastAllways( "La placa del vehiculo no es valida.", decorView)
        ObjectUtils.sleep(2)
    }

    @Throws(InterruptedException::class)
    @Test
    fun makePayment(){
        ObjectUtils.openNavHostFragment()
        ObjectUtils.createMotorcycle("PRV80R", 900)
        ObjectUtils.verifyDisplayToastCondition("Registro exitoso.", "El vehiculo ya se encuentra alquilado.", decorView)
        ObjectUtils.createMotorcycle("QHT94X", 350)
        ObjectUtils.verifyDisplayToastCondition("Registro exitoso.", "El vehiculo ya se encuentra alquilado.", decorView)

        ObjectUtils.click(R.id.navigation_list_rental)
        ObjectUtils.sleep(1)
        ObjectUtils.clickItemRecyclerList(R.id.recyclerRentalList, 0, R.id.contentItemRental)
        ObjectUtils.sleep(2)
        ObjectUtils.click(R.id.btnMakePayment)
        ObjectUtils.sleep(5)

        ObjectUtils.verifyDisplayComponent(R.id.tvDepartureDateVehicle)
        ObjectUtils.verifyDisplayComponent(R.id.tvPriceVehicle)

        ObjectUtils.pressBack()
        ObjectUtils.sleep(2)
    }

}