package com.ceiba.adn_csh

import android.view.View
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import org.hamcrest.Matcher
import org.hamcrest.Matchers


class ObjectUtils {

    companion object{
        @Throws(InterruptedException::class)
        fun sleep(seconds: Long){
            Thread.sleep(seconds * Constants.TIME_OUT)
        }

        fun fillIdEditText(id:Int, text:String){
            Espresso.onView(ViewMatchers.withId(id)).perform(ViewActions.replaceText(text))
        }

        fun click(id:Int){
            Espresso.onView(ViewMatchers.withId(id)).perform(ViewActions.click())
        }

        fun click(text: String){
            Espresso.onView(ViewMatchers.withText(text)).perform(ViewActions.click())
        }

        fun clickItemRecyclerList(id: Int, position: Int, componentIdClick: Int){
            Espresso.onView(ViewMatchers.withId(id))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, clickCardViewWithId(componentIdClick)))
        }

        fun verifyDisplayToastAllways(text: String, decorView: View){
            Espresso.onView(ViewMatchers.withText(text))
                .inRoot(RootMatchers.withDecorView(Matchers.not(decorView)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

        fun verifyDisplayToastCondition(textSuccess: String, textError: String, decorView: View){
            if(!Espresso.onView(ViewMatchers.withText(textSuccess)).inRoot(RootMatchers.withDecorView(Matchers.not(decorView))).isDisplayed()){
                Espresso.onView(ViewMatchers.withText(textError))
                    .inRoot(RootMatchers.withDecorView(Matchers.not(decorView)))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            }else{

            }
        }

        fun verifyDisplayComponent(id: Int){
            Espresso.onView(ViewMatchers.withId(id)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

        fun ViewInteraction.isDisplayed(): Boolean {
            return try {
                check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                true
            } catch (e: NoMatchingViewException) {
                false
            }
        }

        private fun clickCardViewWithId(id: Int): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View>? { return null }
                override fun getDescription(): String { return "Click on a child view with specified id." }
                override fun perform(uiController: UiController, view: View) {
                    val v = view.findViewById<CardView>(id)
                    v.performClick()
                }
            }
        }

        fun createCar(plate: String, cylinderCapacity: Int){
            click(R.id.navigation_create_rental)

            fillIdEditText(R.id.etPlateVehicle, plate)
            sleep(1)
            fillIdEditText(R.id.etCylinderCapacityVehicle, cylinderCapacity.toString())
            click(R.id.btnAddRental)
        }

        fun createMotorcycle(plate: String, cylinderCapacity: Int){
            click(R.id.navigation_create_rental)
            fillIdEditText(R.id.etPlateVehicle, plate)
            sleep(1)
            fillIdEditText(R.id.etCylinderCapacityVehicle, cylinderCapacity.toString())
            sleep(1)
            click(R.id.spinnerVehicleType)
            click("MOTOCICLETA")
            click(R.id.btnAddRental)
        }

        fun openNavHostFragment(){
            Espresso.onView(ViewMatchers.withId(R.id.nav_host_fragment))
        }

        fun pressBack() {
            onView(isRoot()).perform(ViewActions.pressBack())
        }
    }
}