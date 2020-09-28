package com.ceiba.adn_csh.rental

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ceiba.adn_csh.R
import com.ceiba.adn_csh.databinding.RentalCreateFragmentBinding
import com.ceiba.adn_csh.viewmodel.RentalViewModel
import com.ceiba.domain.exception.BusinessException
import com.ceiba.domain.model.Rental
import com.ceiba.domain.model.Vehicle
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.*
import javax.inject.Inject

class RentalCreateFragment : Fragment() {

    private lateinit var rentalCreateFragmentBinding: RentalCreateFragmentBinding

    @Inject
    lateinit var modelViewFactory: ViewModelProvider.Factory

    private val rentalViewModel by lazy {
        ViewModelProvider(this, modelViewFactory)[RentalViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this@RentalCreateFragment)
        super.onAttach(context)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rentalCreateFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.rental_create_fragment, container, false)
        return rentalCreateFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val typeVehicles = arrayOf(getString(R.string.car), getString(R.string.motorcycle))
        val arrayAdapterSpinnerVehicle: ArrayAdapter<String> = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, typeVehicles)
        rentalCreateFragmentBinding.spinnerVehicleType.adapter = arrayAdapterSpinnerVehicle

        rentalCreateFragmentBinding.btnAddRental.setOnClickListener {
            getDataFormToCreateRental()
        }
    }

    private fun getDataFormToCreateRental() {
        val plate = rentalCreateFragmentBinding.etPlateVehicle.text.toString()
        val cylinderCapacity = rentalCreateFragmentBinding.etCylinderCapacityVehicle.text.toString()
        val typeVehicle = rentalCreateFragmentBinding.spinnerVehicleType.selectedItem.toString()
        if(plate.isEmpty()){
            Toast.makeText(requireActivity(), getString(R.string.you_must_enter_the_plate), Toast.LENGTH_SHORT).show()
            return
        }
        if(cylinderCapacity.isEmpty()){
            Toast.makeText(requireActivity(), getString(R.string.you_must_enter_the_cylinder), Toast.LENGTH_SHORT).show()
            return
        }
        createRental(plate, cylinderCapacity.toInt(), typeVehicle)
    }

    private fun createRental(plate: String, cylinderCapacity: Int, typeVehicle: String) {
        lifecycleScope.launch {
            try {
                val vehicle = Vehicle(plate, cylinderCapacity, typeVehicle)
                val rental = Rental(id = 0, vehicle = vehicle, arrivalDate = Date(), departureDate = Date(), price = 0.0, active = true)
                withContext(Dispatchers.IO){ rentalViewModel.createRental(rental) }
                Toast.makeText(requireActivity(), getString(R.string.successful_registration), Toast.LENGTH_SHORT).show()
                rentalCreateFragmentBinding.etPlateVehicle.setText("")
                rentalCreateFragmentBinding.etCylinderCapacityVehicle.setText("")
            }catch (e: BusinessException){
                Toast.makeText(requireActivity(), e.message, Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
                Log.e("CreateRental", e.message, e)
                Toast.makeText(requireActivity(), getString(R.string.rent_could_not_be_added_please_try_again_later), Toast.LENGTH_SHORT).show()
            }
        }
    }
}